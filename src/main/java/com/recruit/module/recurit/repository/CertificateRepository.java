package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.entity.Certificate;
import com.recruit.module.recurit.entity.Recruit;
import com.recruit.module.recurit.repository.custom.CertificateCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long>, CertificateCustomRepository {
    List<Certificate> findByRecruit(Recruit recruit);
}
