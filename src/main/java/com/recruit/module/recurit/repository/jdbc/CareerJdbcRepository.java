package com.recruit.module.recurit.repository.jdbc;

import com.recruit.module.recurit.dto.vo.CareerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CareerJdbcRepository implements BatchJdbcRepository<CareerVo> {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<CareerVo> list, Long parentId) {

        if (list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                INSERT INTO "CAREER"
                    (
                    	 "CAR_SEQ", "REC_SEQ", "COMP_NAME", "LOC_SEQ",
                    	  "START_PERIOD", "END_PERIOD", "TASK"
                    )
                VALUES
                    (
                        SEQ_CAREER.NEXTVAL, ?, ?, ?, ?, ?, ?
                    )
                """;
        int[][] insertCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, CareerVo vo) -> {
                    ps.setLong(1, parentId);
                    ps.setString(2, vo.getCompName());
                    ps.setLong(3, vo.getLocSeq());
                    ps.setDate(4, java.sql.Date.valueOf(vo.getStartPeriod()));
                    ps.setDate(5, java.sql.Date.valueOf(vo.getEndPeriod()));
                    ps.setString(6, vo.getTask());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<CareerVo> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                UPDATE
                    "CAREER"
                SET
                    "COMP_NAME" = ?, "LOC_SEQ" = ?, "START_PERIOD" = ?, "END_PERIOD" = ?, "TASK" = ?
                WHERE
                    "CAR_SEQ" = ?
                """;
        int[][] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, CareerVo vo) -> {
                    ps.setString(1, vo.getCompName());
                    ps.setLong(2, vo.getLocSeq());
                    ps.setDate(3, java.sql.Date.valueOf(vo.getStartPeriod()));
                    ps.setDate(4, java.sql.Date.valueOf(vo.getEndPeriod()));
                    ps.setString(5, vo.getTask());
                    ps.setLong(6, vo.getCarSeq());
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
                DELETE FROM "CAREER" WHERE "CAR_SEQ" = ?
                """;

        int[][] deleteCount = jdbcTemplate.batchUpdate(
                sql,
                idList,
                idList.size(),
                (PreparedStatement ps, Long carSeq) -> ps.setLong(1, carSeq)
        );

        return batchReturn(deleteCount);
    }
}
