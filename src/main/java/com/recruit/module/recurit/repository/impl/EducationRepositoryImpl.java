package com.recruit.module.recurit.repository.impl;

import com.recruit.module.recurit.dto.request.EducationRequestDto;
import com.recruit.module.recurit.repository.custom.BatchJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements BatchJdbcRepository<EducationRequestDto> {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<EducationRequestDto> list, Long parentId) {

        if (list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                INSERT INTO
                EDUCATION (
                	EDU_SEQ, REC_SEQ, SCHOOL_NAME, SCHOOL_TYPE, DIVISION,
                	START_PERIOD, END_PERIOD, MAJOR, GRADE, LOC_SEQ
                	)
                VALUES ( +
                	SEQ_EDUCATION.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?
                	)
                """;

        int[][] insertCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, EducationRequestDto dto) -> {
                    ps.setLong(1, parentId);
                    ps.setString(2, dto.getSchoolName());
                    ps.setString(3, dto.getSchoolType().getValue());
                    ps.setString(4, dto.getDivision().getValue());
                    ps.setDate(5, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(6, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(7, dto.getMajor());
                    ps.setBigDecimal(8, dto.getGrade());
                    ps.setLong(9, dto.getLocSeq());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<EducationRequestDto> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                UPDATE
                    EDUCATION
                SET
                    SCHOOL_NAME = ?, SCHOOL_TYPE = ?, DIVISION = ?,
                    START_PERIOD = ?, END_PERIOD = ?, MAJOR = ?,
                    GRADE = ?, LOC_SEQ = ?
                WHERE
                    EDU_SEQ = ?
                """;

        int[][] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, EducationRequestDto dto) -> {
                    ps.setString(1, dto.getSchoolName());
                    ps.setString(2, dto.getSchoolType().getValue());
                    ps.setString(3, dto.getDivision().getValue());
                    ps.setDate(4, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(5, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(6, dto.getMajor());
                    ps.setBigDecimal(7, dto.getGrade());
                    ps.setLong(8, dto.getLocSeq());
                    ps.setLong(9, dto.getEduSeq());
                }
        );

        return batchReturn(updateCounts);
    }

    @Override
    public int batchDelete(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
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
                idList,
                idList.size(),
                (PreparedStatement ps, Long eduSeq) -> ps.setLong(1, eduSeq)
        );

        return batchReturn(deleteCount);
    }
}
