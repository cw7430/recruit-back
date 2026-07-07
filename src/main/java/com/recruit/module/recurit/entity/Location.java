package com.recruit.module.recurit.entity;

import com.recruit.module.recurit.dto.response.LocationResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "LOCATION")
@SequenceGenerator(
        name = "LOC_SEQ_GENERATOR",
        sequenceName = "SEQ_LOCATION",
        allocationSize = 1
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {
    @Id
    @Column(name = "LOC_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOC_SEQ_GENERATOR")
    private Long locSeq;

    @Column(name = "LOC_NAME")
    private String locName;

    public static List<LocationResponseDto> toDtoList(List<Location> locationList) {
        return locationList.stream().map(location -> new LocationResponseDto(
                location.getLocSeq(),
                location.getLocName()
        )).toList();
    }
}
