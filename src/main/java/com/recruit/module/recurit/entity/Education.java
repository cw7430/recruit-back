package com.recruit.module.recurit.entity;


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
    @Column(name = "EDU_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDU_SEQ_GENERATOR")
    private Long eduSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_EDUCATION_1"))
    private Recruit recruit;

    @Column(name = "SCHOOL_NAME")
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCHOOL_TYPE")
    private SchoolType schoolType;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIVISION")
    private Division division;

    @Column(name = "START_PERIOD")
    private LocalDate startPeriod;

    @Column(name = "END_PERIOD")
    private LocalDate endPeriod;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "GRADE")
    private BigDecimal grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_SEQ", foreignKey = @ForeignKey(name = "FK_LOCATION_TO_EDUCATION_1"))
    private Location location = null;
}
