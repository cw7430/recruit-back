package com.recruit.module.recurit.repository.impl;

import com.recruit.module.recurit.repository.custom.CertificateCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CertificateRepositoryImpl implements CertificateCustomRepository {
    private final JdbcTemplate jdbcTemplate;
}
