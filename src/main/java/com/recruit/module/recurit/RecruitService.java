package com.recruit.module.recurit;

import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;
import com.recruit.common.config.security.JwtUtil;
import com.recruit.module.recurit.dto.response.LocationResponseDto;
import com.recruit.module.recurit.dto.response.RecruitResponseDto;
import com.recruit.module.recurit.entity.Location;
import com.recruit.module.recurit.entity.Recruit;
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
}
