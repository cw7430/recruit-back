package com.recruit.module.recurit.repository.impl;

import com.recruit.module.recurit.dto.request.CareerRequestDto;
import com.recruit.module.recurit.repository.custom.BatchJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CareerRepositoryImpl implements BatchJdbcRepository<CareerRequestDto> {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<CareerRequestDto> list, Long parentId) {

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
                (PreparedStatement ps, CareerRequestDto dto) -> {
                    ps.setLong(1, parentId);
                    ps.setString(2, dto.getCompName());
                    ps.setLong(3, dto.getLocSeq());
                    ps.setDate(4, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(5, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(6, dto.getTask());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<CareerRequestDto> list) {
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
                (PreparedStatement ps, CareerRequestDto dto) -> {
                    ps.setString(1, dto.getCompName());
                    ps.setLong(2, dto.getLocSeq());
                    ps.setDate(3, java.sql.Date.valueOf(dto.getStartPeriod()));
                    ps.setDate(4, java.sql.Date.valueOf(dto.getEndPeriod()));
                    ps.setString(5, dto.getTask());
                    ps.setLong(6, dto.getCarSeq());
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
