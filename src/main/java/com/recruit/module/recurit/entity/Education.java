package com.recruit.module.recurit.entity;


import com.recruit.module.recurit.dto.response.EducationResponseDto;
import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EDUCATION")
@SequenceGenerator(
        name = "EDU_SEQ_GENERATOR",
        sequenceName = "SEQ_EDUCATION",
        allocationSize = 1
)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Education {
    @Id
    @Column(name = "EDU_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDU_SEQ_GENERATOR")
    private Long eduSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_EDUCATION_1"))
    private Recruit recruit;

    @Column(name = "SCHOOL_NAME", nullable = false)
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCHOOL_TYPE", nullable = false)
    private SchoolType schoolType;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIVISION", nullable = false)
    private Division division;

    @Column(name = "START_PERIOD", nullable = false)
    private LocalDate startPeriod;

    @Column(name = "END_PERIOD", nullable = false)
    private LocalDate endPeriod;

    @Column(name = "MAJOR", nullable = false)
    private String major;

    @Column(name = "GRADE", nullable = false)
    private BigDecimal grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_LOCATION_TO_EDUCATION_1"))
    private Location location = null;

    public static List<EducationResponseDto> toDtoList(List<Education> educationList) {
        return educationList.stream().map(education -> new EducationResponseDto(
                education.getEduSeq(),
                education.getSchoolName(),
                education.getSchoolType(),
                education.getDivision(),
                education.getStartPeriod(),
                education.getEndPeriod(),
                education.getMajor(),
                education.getGrade(),
                education.getLocation().getLocSeq()
        )).toList();
    }
}
