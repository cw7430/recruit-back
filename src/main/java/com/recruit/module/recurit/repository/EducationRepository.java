package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.dto.request.EducationRequestDto;
import com.recruit.module.recurit.entity.Education;
import com.recruit.module.recurit.repository.custom.BatchJdbcRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long>, BatchJdbcRepository<EducationRequestDto> {
}
