package com.recruit.module.recurit.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "CERTIFICATE")
@SequenceGenerator(
        name = "CERT_SEQ_GENERATOR",
        sequenceName = "SEQ_CERTIFICATE",
        allocationSize = 1
)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Certificate {
    @Id
    @Column(name = "CERT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERT_SEQ_GENERATOR")
    private Long certSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_CERTIFICATE_1"))
    private Recruit recruit;

    @Column(name = "QUALIFY_NAME")
    private String qualifyName;

    @Column(name = "ACQU_DATE")
    private LocalDate acquDate;

    @Column(name = "ORGANIZE_NAME")
    private String organizeName;
}
