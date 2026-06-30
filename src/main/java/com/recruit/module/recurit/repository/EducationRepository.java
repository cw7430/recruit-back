package com.recruit.module.recurit.repository;

import com.recruit.module.recurit.entity.Education;
import com.recruit.module.recurit.entity.Recruit;
import com.recruit.module.recurit.repository.custom.EducationCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long>, EducationCustomRepository {
    List<Education> findByRecruit(Recruit recruit);
}
