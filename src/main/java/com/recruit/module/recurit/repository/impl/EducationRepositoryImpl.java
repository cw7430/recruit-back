package com.recruit.module.recurit.repository.impl;

import com.recruit.module.recurit.dto.request.EducationRequestDto;
import com.recruit.module.recurit.repository.custom.EducationCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements EducationCustomRepository {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<EducationRequestDto> educationList, Long recruitSeq) {

        if (educationList == null || educationList.isEmpty()) {
            return 0;
        }

        String sql = """
                INSERT INTO
                EDUCATION (
                	EDU_SEQ, REC_SEQ, SCHOOL_NAME, SCHOOL_TYPE, DIVISION,
                	START_PERIOD, END_PERIOD, MAJOR, GRADE, LOCATION
                	)
                VALUES ( +
                	SEQ_EDUCATION.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?
                	)
                """;

        int[][] insertCounts = jdbcTemplate.batchUpdate(
                sql,
                educationList,
                educationList.size(),
                (PreparedStatement ps, EducationRequestDto dto) -> {
                    ps.setLong(1, recruitSeq);
                    ps.setString(2, dto.getSchoolName());
                    ps.setString(3, dto.getSchoolType());
                    ps.setString(4, dto.getDivision());
                    ps.setDate(5, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(6, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(7, dto.getMajor());
                    ps.setBigDecimal(8, dto.getGrade());
                    ps.setString(9, dto.getLocation());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<EducationRequestDto> educationList) {
        if (educationList == null || educationList.isEmpty()) {
            return 0;
        }

        String sql = """
                UPDATE
                    EDUCATION
                SET
                    SCHOOL_NAME = ?, SCHOOL_TYPE = ?, DIVISION = ?,
                    START_PERIOD = ?, END_PERIOD = ?, MAJOR = ?,
                    GRADE = ?, LOCATION = ?
                WHERE
                    EDU_SEQ = ?
                """;

        int[][] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                educationList,
                educationList.size(),
                (PreparedStatement ps, EducationRequestDto dto) -> {
                    ps.setString(1, dto.getSchoolName());
                    ps.setString(2, dto.getSchoolType());
                    ps.setString(3, dto.getDivision());
                    ps.setDate(4, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(5, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(6, dto.getMajor());
                    ps.setBigDecimal(7, dto.getGrade());
                    ps.setString(8, dto.getLocation());
                    ps.setLong(9, dto.getEduSeq());
                }
        );

        return batchReturn(updateCounts);
    }

    @Override
    public int batchDelete(List<Long> eduSeqList) {
        if (eduSeqList == null || eduSeqList.isEmpty()) {
            return 0;
        }

        String sql = """
                DELETE FROM
                    EDUCATION
                WHERE
                    EDU_SEQ = ?
                """;

        int[][] deleteCount = jdbcTemplate.batchUpdate(
                sql,
                eduSeqList,
                eduSeqList.size(),
                (PreparedStatement ps, Long eduSeq) -> ps.setLong(1, eduSeq)
        );

        return batchReturn(deleteCount);
    }
}
