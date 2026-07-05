package com.recruit.module.recurit.repository.jpa;

import com.recruit.module.recurit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    Optional<Recruit> findByNameAndPhone(String name, String phone);
}
