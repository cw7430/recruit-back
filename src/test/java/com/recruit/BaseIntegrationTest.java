package com.recruit;

import com.recruit.module.recurit.entity.Recruit;
import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.Submit;
import com.recruit.module.recurit.entity.type.WorkType;
import com.recruit.module.recurit.repository.jpa.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/init_schema.sql")
@Sql(scripts = {"/clean_schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public abstract class BaseIntegrationTest {

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        String testPasswordHash = passwordEncoder.encode("password1234!");
        Recruit insertedRecruit = recruitRepository.save(
                Recruit.create("테스트", "010-0000-0000", testPasswordHash)
        );
        insertedRecruit.updateInfo(
                LocalDate.parse("1996-01-01"), Gender.M, "example@example.com",
                "서울특별시 서초구 반포동", insertedRecruit.getLocation(), WorkType.FULL_TIME, Submit.N
        );
        recruitRepository.save(insertedRecruit);

        Recruit submittedRecruit = recruitRepository.save(
                Recruit.create("지원완료", "010-0000-0000", testPasswordHash)
        );
        submittedRecruit.updateInfo(
                LocalDate.parse("1996-01-01"), Gender.M, "example@example.com",
                "서울특별시 서초구 반포동", submittedRecruit.getLocation(), WorkType.FULL_TIME, Submit.Y
        );
        recruitRepository.save(submittedRecruit);
    }
}
