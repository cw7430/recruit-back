package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.entity.Career;
import com.recruit.module.recurit.entity.Recruit;
import com.recruit.module.recurit.repository.custom.CareerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long>, CareerCustomRepository {
    List<Career> findByRecruit(Recruit recruit);
}
