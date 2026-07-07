package com.recruit.module.recurit.entity;

import com.recruit.module.recurit.dto.response.CertificateResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "CERT_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERT_SEQ_GENERATOR")
    private Long certSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_SEQ", nullable = false, foreignKey = @ForeignKey(name = "FK_RECRUIT_TO_CERTIFICATE_1"))
    private Recruit recruit;

    @Column(name = "QUALIFY_NAME", nullable = false)
    private String qualifyName;

    @Column(name = "ACQU_DATE", nullable = false)
    private LocalDate acquDate;

    @Column(name = "ORGANIZE_NAME", nullable = false)
    private String organizeName;

    public static List<CertificateResponseDto> toDtoList(List<Certificate> certificateList) {
        return certificateList.stream().map(certificate -> new CertificateResponseDto(
                certificate.getCertSeq(),
                certificate.getQualifyName(),
                certificate.getAcquDate(),
                certificate.getOrganizeName()
        )).toList();
    }
}
