package com.recruit.module.recurit.repository.impl;

import com.recruit.module.recurit.repository.custom.CareerCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CareerRepositoryImpl implements CareerCustomRepository {
    private final JdbcTemplate jdbcTemplate;
}
