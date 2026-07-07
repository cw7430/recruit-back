package com.recruit.module.recurit.entity;

import com.recruit.module.recurit.dto.response.CareerResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "CAR_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ_GENERATOR")
    private Long carSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_CAREER_1"))
    private Recruit recruit;

    @Column(name = "COMP_NAME", nullable = false)
    private String compName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_LOCATION_TO_CAREER_1"))
    private Location location;

    @Column(name = "START_PERIOD", nullable = false)
    private LocalDate startPeriod;

    @Column(name = "END_PERIOD", nullable = false)
    private LocalDate endPeriod;

    @Column(name = "TASK", nullable = false)
    private String task;

    public static List<CareerResponseDto> toDtoList(List<Career> careerList) {
        return careerList.stream().map(career -> new CareerResponseDto(
                career.getCarSeq(),
                career.getCompName(),
                career.getLocation().getLocSeq(),
                career.getStartPeriod(),
                career.getEndPeriod(),
                career.getTask()
        )).toList();
    }
}
