package com.recruit.module.recurit;

import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;
import com.recruit.common.config.security.JwtUtil;
import com.recruit.module.recurit.dto.request.CareerRequestDto;
import com.recruit.module.recurit.dto.request.CertificateRequestDto;
import com.recruit.module.recurit.dto.request.EducationRequestDto;
import com.recruit.module.recurit.dto.request.RecruitSubmitRequestDto;
import com.recruit.module.recurit.dto.response.LocationResponseDto;
import com.recruit.module.recurit.dto.response.RecruitResponseDto;
import com.recruit.module.recurit.dto.vo.*;
import com.recruit.module.recurit.entity.*;
import com.recruit.module.recurit.repository.jdbc.CareerJdbcRepository;
import com.recruit.module.recurit.repository.jdbc.CertificateJdbcRepository;
import com.recruit.module.recurit.repository.jdbc.EducationJdbcRepository;
import com.recruit.module.recurit.repository.jpa.LocationRepository;
import com.recruit.module.recurit.repository.jpa.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final LocationRepository locationRepository;
    private final EducationJdbcRepository educationJdbcRepository;
    private final CareerJdbcRepository careerJdbcRepository;
    private final CertificateJdbcRepository certificateJdbcRepository;
    private final JwtUtil jwtUtil;

    public RecruitResponseDto getRecruit() {
        Long recSeq = jwtUtil.getCurrentUserId();
        Recruit recruit = recruitRepository.findById(recSeq)
                .orElseThrow(() -> new CustomException(ResponseCode.RESOURCE_NOT_FOUND));
        List<LocationResponseDto> locationList = Location.toDtoList(locationRepository.findAll());
        log.info("Get Recruit By recSeq : {}", recruit.getRecSeq());
        return Recruit.toDto(recruit, locationList);
    }

    @Transactional
    public void recruitAction(RecruitSubmitRequestDto reqDto) {
        Long recSeq = jwtUtil.getCurrentUserId();
        Recruit recruit = recruitRepository.findById(recSeq)
                .orElseThrow(() -> new CustomException(ResponseCode.RESOURCE_NOT_FOUND));
        Location recruitLocation = locationRepository.findById(reqDto.getLocSeq())
                .orElseThrow(() -> new CustomException(ResponseCode.RESOURCE_NOT_FOUND));
        recruit.updateInfo(
                reqDto.getBirth(),
                reqDto.getGender(),
                reqDto.getEmail(),
                reqDto.getAddress(),
                recruitLocation,
                reqDto.getWorkType(),
                reqDto.getSubmit()
        );

        educationAction(recruit, reqDto, recSeq);
        careerAction(recruit, reqDto, recSeq);
        certificateAction(recruit, reqDto, recSeq);

        log.info("Recruit Action Complete : {}", recruit.getRecSeq());
    }

    private void educationAction(Recruit recruit, RecruitSubmitRequestDto reqDto, Long recSeq) {

        List<EducationVo> oldEducationList =
                recruit.getEducationList().isEmpty()
                        ? List.of()
                        : Education.toVoList(recruit.getEducationList());

        List<EducationVo> newEducationList =
                reqDto.getEducationList().isEmpty() ? List.of() :
                EducationRequestDto.toVoList(reqDto.getEducationList());

        if (oldEducationList.isEmpty() && newEducationList.isEmpty()) {
            return;
        }

        ClassifiedListVo<EducationVo> classifiedEducationList = ClassifiedListVo.getClassifiedList(oldEducationList, newEducationList);

        if (periodValidator(classifiedEducationList.insertList(), classifiedEducationList.updateList())) {
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "education: period",
                    "날짜가 겹치지 않아야 합니다."
            );
        }

        if (!classifiedEducationList.insertList().isEmpty()) {
            int insertCnt = educationJdbcRepository.batchInsert(classifiedEducationList.insertList(), recSeq);
            log.info("Insert {} Education", insertCnt);
        }

        if (!classifiedEducationList.updateList().isEmpty()) {
            int updateCnt = educationJdbcRepository.batchUpdate(classifiedEducationList.updateList());
            log.info("Update {} Education", updateCnt);
        }

        if (!classifiedEducationList.deleteSeqList().isEmpty()) {
            int deleteCnt = educationJdbcRepository.batchDelete(classifiedEducationList.deleteSeqList());
            log.info("Delete {} Education", deleteCnt);
        }
    }

    private void careerAction(Recruit recruit, RecruitSubmitRequestDto reqDto, Long recSeq) {
        List<CareerVo> oldCareerList = recruit.getCareerList().isEmpty() ? List.of() :
                Career.toVoList(recruit.getCareerList());

        List<CareerVo> newCareerList = reqDto.getCareerList().isEmpty() ? List.of() :
                CareerRequestDto.toVoList(reqDto.getCareerList());

        if (oldCareerList.isEmpty() && newCareerList.isEmpty()) {
            return;
        }

        ClassifiedListVo<CareerVo> classifiedCareerList = ClassifiedListVo.getClassifiedList(oldCareerList, newCareerList);

        if (periodValidator(classifiedCareerList.insertList(), classifiedCareerList.updateList())) {
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "career: period",
                    "날짜가 겹치지 않아야 합니다."
            );
        }

        if (!classifiedCareerList.insertList().isEmpty()) {
            int insertCnt = careerJdbcRepository.batchInsert(classifiedCareerList.insertList(), recSeq);
            log.info("Insert {} Career", insertCnt);
        }

        if (!classifiedCareerList.updateList().isEmpty()) {
            int updateCnt = careerJdbcRepository.batchUpdate(classifiedCareerList.updateList());
            log.info("Update {} Career", updateCnt);
        }

        if (!classifiedCareerList.deleteSeqList().isEmpty()) {
            int deleteCnt = careerJdbcRepository.batchDelete(classifiedCareerList.deleteSeqList());
            log.info("Delete {} Career", deleteCnt);
        }
    }

    private void certificateAction(Recruit recruit, RecruitSubmitRequestDto reqDto, Long recSeq) {
        List<CertificateVo> oldCertificateList = recruit.getCertificateList().isEmpty() ? List.of() :
                Certificate.toVoList(recruit.getCertificateList());

        List<CertificateVo> newCertificateList = reqDto.getCertificateList().isEmpty() ? List.of() :
                CertificateRequestDto.toVoList(reqDto.getCertificateList());

        if (oldCertificateList.isEmpty() && newCertificateList.isEmpty()) {
            return;
        }

        ClassifiedListVo<CertificateVo> classifiedCertificateList = ClassifiedListVo.getClassifiedList(oldCertificateList, newCertificateList);

        if (!classifiedCertificateList.insertList().isEmpty()) {
            int insertCnt = certificateJdbcRepository.batchInsert(classifiedCertificateList.insertList(), recSeq);
            log.info("Insert {} Certificate", insertCnt);
        }

        if (!classifiedCertificateList.updateList().isEmpty()) {
            int updateCnt = certificateJdbcRepository.batchUpdate(classifiedCertificateList.updateList());
            log.info("Update {} Certificate", updateCnt);
        }

        if (!classifiedCertificateList.deleteSeqList().isEmpty()) {
            int deleteCnt = certificateJdbcRepository.batchDelete(classifiedCertificateList.deleteSeqList());
            log.info("Delete {} Certificate", deleteCnt);
        }
    }

    private static <T extends PeriodVo> boolean periodValidator(List<T> insertList, List<T> updateList) {
        List<T> mergedList = Stream.concat(insertList.stream(), updateList.stream())
                        .sorted(Comparator.comparing(T::getStartPeriod)).toList();

        for (int i = 1; i < mergedList.size(); i++) {
            T previous = mergedList.get(i - 1);
            T current = mergedList.get(i);

            if (previous.getEndPeriod().isAfter(current.getStartPeriod())) {
                return true;
            }
        }

        return false;
    }
}
