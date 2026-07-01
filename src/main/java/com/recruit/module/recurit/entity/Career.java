package com.recruit.module.recurit.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "CAREER")
@SequenceGenerator(
        name = "CAR_SEQ_GENERATOR",
        sequenceName = "SEQ_CAREER",
        allocationSize = 1
)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Career {
    @Id
    @Column(name = "CAR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ_GENERATOR")
    private Long carSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_CAREER_1"))
    private Recruit recruit;

    @Column(name = "COMP_NAME")
    private String compName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_SEQ", foreignKey = @ForeignKey(name = "FK_LOCATION_TO_CAREER_1"))
    private Location location;

    @Column(name = "START_PERIOD")
    private LocalDate startPeriod;

    @Column(name = "END_PERIOD")
    private LocalDate endPeriod;

    @Column(name = "TASK")
    private String task;
}
