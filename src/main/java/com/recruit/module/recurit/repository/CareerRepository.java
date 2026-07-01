package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.dto.request.CareerRequestDto;
import com.recruit.module.recurit.entity.Career;
import com.recruit.module.recurit.repository.custom.BatchJdbcRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long>, BatchJdbcRepository<CareerRequestDto> {
}
