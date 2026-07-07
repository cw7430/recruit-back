package com.recruit.module.recurit.entity;

import com.recruit.module.recurit.dto.response.*;
import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.Submit;
import com.recruit.module.recurit.entity.type.WorkType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RECRUIT")
@SequenceGenerator(
        name = "REC_SEQ_GENERATOR",
        sequenceName = "SEQ_RECRUIT",
        allocationSize = 1
)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recruit {
    @Id
    @Column(name = "REC_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REC_SEQ_GENERATOR")
    private Long recSeq;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "BIRTH")
    private LocalDate birth = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender = null;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL")
    private String email = null;

    @Lob
    @Column(name = "ADDRESS")
    private String address = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOC_SEQ", foreignKey = @ForeignKey(name = "FK_LOCATION_TO_RECRUIT_1"))
    private Location location = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "WORK_TYPE")
    private WorkType workType = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBMIT", nullable = false)
    private Submit submit = Submit.N;

    @OneToMany(mappedBy = "recruit")
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(mappedBy = "recruit")
    private List<Career> careerList = new ArrayList<>();

    @OneToMany(mappedBy = "recruit")
    private List<Certificate> certificateList = new ArrayList<>();

    public static RecruitResponseDto toDto(Recruit recruit, List<LocationResponseDto> locationList) {
        List<EducationResponseDto> educationList =
                recruit.getEducationList().isEmpty() ? null : Education.toDtoList(recruit.getEducationList());
        List<CareerResponseDto> careerList =
                recruit.getCareerList().isEmpty() ? null : Career.toDtoList(recruit.getCareerList());
        List<CertificateResponseDto> certificateList =
                recruit.getCertificateList().isEmpty() ? null : Certificate.toDtoList(recruit.getCertificateList());
        return new RecruitResponseDto(
                recruit.getRecSeq(),
                recruit.getName(),
                recruit.getBirth(),
                recruit.getGender(),
                recruit.getPhone(),
                recruit.getEmail(),
                recruit.getAddress(),
                recruit.getLocation() == null ? null : recruit.getLocation().getLocSeq(),
                recruit.getWorkType(),
                educationList,
                careerList,
                certificateList,
                locationList
        );
    }

    public static Recruit create(String name, String phone, String passwordHash) {
        Recruit recruit = new Recruit();
        recruit.name = name;
        recruit.phone = phone;
        recruit.passwordHash = passwordHash;
        recruit.submit = Submit.N;
        return recruit;
    }

    public void updateInfo(
            LocalDate birth,
            Gender gender,
            String email,
            String address,
            Location location,
            WorkType workType,
            Submit submit
    ) {
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.location = location;
        this.workType = workType;
        this.submit = submit;
    }
}
