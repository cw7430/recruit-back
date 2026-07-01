package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.dto.request.CertificateRequestDto;
import com.recruit.module.recurit.entity.Certificate;
import com.recruit.module.recurit.repository.custom.BatchJdbcRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long>, BatchJdbcRepository<CertificateRequestDto> {
}
