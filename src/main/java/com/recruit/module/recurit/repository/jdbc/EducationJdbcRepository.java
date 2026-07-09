package com.recruit.module.recurit.repository.jdbc;

import com.recruit.module.recurit.dto.vo.EducationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EducationJdbcRepository implements BatchJdbcRepository<EducationVo> {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<EducationVo> list, Long parentId) {

        if (list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                INSERT INTO
                EDUCATION (
                	EDU_SEQ, REC_SEQ, SCHOOL_NAME, SCHOOL_TYPE, DIVISION,
                	START_PERIOD, END_PERIOD, MAJOR, GRADE, LOC_SEQ
                	)
                VALUES (
                	SEQ_EDUCATION.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?
                	)
                """;

        int[][] insertCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, EducationVo vo) -> {
                    ps.setLong(1, parentId);
                    ps.setString(2, vo.getSchoolName());
                    ps.setString(3, vo.getSchoolType().getValue());
                    ps.setString(4, vo.getDivision().getValue());
                    ps.setDate(5, java.sql.Date.valueOf(vo.getStartPeriod()));
                    ps.setDate(6, java.sql.Date.valueOf(vo.getEndPeriod()));
                    ps.setString(7, vo.getMajor());
                    ps.setBigDecimal(8, vo.getGrade());
                    ps.setLong(9, vo.getLocSeq());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<EducationVo> list) {
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
                (PreparedStatement ps, EducationVo vo) -> {
                    ps.setString(1, vo.getSchoolName());
                    ps.setString(2, vo.getSchoolType().getValue());
                    ps.setString(3, vo.getDivision().getValue());
                    ps.setDate(4, java.sql.Date.valueOf(vo.getStartPeriod()));
                    ps.setDate(5, java.sql.Date.valueOf(vo.getEndPeriod()));
                    ps.setString(6, vo.getMajor());
                    ps.setBigDecimal(7, vo.getGrade());
                    ps.setLong(8, vo.getLocSeq());
                    ps.setLong(9, vo.getEduSeq());
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
