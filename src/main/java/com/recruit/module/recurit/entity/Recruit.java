package com.recruit.module.recurit.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

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
    @Column(name = "REC_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REC_SEQ_GENERATOR")
    private Long recSeq;

    @Column(name = "NAME")
    private String name;

    @Column(name="PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "BIRTH")
    private LocalDate birth = null;

    @Column(name = "GENDER")
    private String gender = null;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email = null;

    @Lob
    @Column(name = "ADDRESS")
    private String address = null;

    @Column(name = "LOCATION")
    private String location = null;

    @Column(name = "WORK_TYPE")
    private String workType = null;

    @Column(name = "SUBMIT")
    private String submit = "N";

    public static Recruit create(String name, String phone, String passwordHash) {
        Recruit recruit = new Recruit();
        recruit.name = name;
        recruit.phone = phone;
        recruit.passwordHash = passwordHash;
        recruit.submit = "N";
        return recruit;
    }

    public void updateInfo(LocalDate birth, String gender, String email, String address, String location, String workType, String submit) {
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.location = location;
        this.workType = workType;
        this.submit = submit;
    }
}
