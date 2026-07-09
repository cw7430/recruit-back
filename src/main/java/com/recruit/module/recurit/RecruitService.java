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
import com.recruit.module.recurit.dto.vo.CareerVo;
import com.recruit.module.recurit.dto.vo.CertificateVo;
import com.recruit.module.recurit.dto.vo.EducationVo;
import com.recruit.module.recurit.entity.*;
import com.recruit.module.recurit.repository.jdbc.CareerJdbcRepository;
import com.recruit.module.recurit.repository.jdbc.CertificateJdbcRepository;
import com.recruit.module.recurit.repository.jdbc.EducationJdbcRepository;
import com.recruit.module.recurit.repository.jpa.LocationRepository;
import com.recruit.module.recurit.repository.jpa.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Long reqSeq = jwtUtil.getCurrentUserId();
        Recruit recruit = recruitRepository.findById(reqSeq)
                .orElseThrow(() -> new CustomException(ResponseCode.RESOURCE_NOT_FOUND));
        List<LocationResponseDto> locationList = Location.toDtoList(locationRepository.findAll());
        log.info("Get Recruit By recSeq : {}", recruit.getRecSeq());
        return Recruit.toDto(recruit, locationList);
    }

    public void recruitAction(RecruitSubmitRequestDto reqDto) {
        Long reqSeq = jwtUtil.getCurrentUserId();
        Recruit recruit = recruitRepository.findById(reqSeq)
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

        List<EducationVo> oldEducationList = recruit.getEducationList().isEmpty() ? null :
                Education.toVoList(recruit.getEducationList());
        List<CareerVo> oldCareerList = recruit.getCareerList().isEmpty() ? null :
                Career.toVoList(recruit.getCareerList());
        List<CertificateVo> oldCertificateList = recruit.getCertificateList().isEmpty() ? null :
                Certificate.toVoList(recruit.getCertificateList());

        List<EducationVo> newEducationList = reqDto.getEducationList().isEmpty() ? null :
                EducationRequestDto.toVoList(reqDto.getEducationList());
        List<CareerVo> newCareerList = reqDto.getCareerList().isEmpty() ? null :
                CareerRequestDto.toVoList(reqDto.getCareerList());
        List<CertificateVo> newCertificateList = reqDto.getCertificateList().isEmpty() ? null :
                CertificateRequestDto.toVoList(reqDto.getCertificateList());
    }
}
