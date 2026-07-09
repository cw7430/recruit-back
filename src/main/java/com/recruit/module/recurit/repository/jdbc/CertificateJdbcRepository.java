package com.recruit.module.recurit.repository.jdbc;

import com.recruit.module.recurit.dto.vo.CertificateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificateJdbcRepository implements BatchJdbcRepository<CertificateVo> {
    private final JdbcTemplate jdbcTemplate;

    private static int batchReturn(int[][] counts) {
        return Arrays.stream(counts)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    @Override
    public int batchInsert(List<CertificateVo> list, Long parentId) {
        if(list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                INSERT INTO "CERTIFICATE"
                    (
                        CERT_SEQ, REC_SEQ, QUALIFY_NAME, ACQU_DATE, ORGANIZE_NAME
                    )
                VALUES
                    (
                        SEQ_CERTIFICATE.NEXTVAL, ?, ?, ?, ?
                    )
                """;
        int[][] insertCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, CertificateVo vo) -> {
                    ps.setLong(1, parentId);
                    ps.setString(2, vo.getQualifyName());
                    ps.setDate(3, java.sql.Date.valueOf(vo.getAcquDate()));
                    ps.setString(4, vo.getOrganizeName());
                }
        );

        return batchReturn(insertCounts);
    }

    @Override
    public int batchUpdate(List<CertificateVo> list) {
        if(list == null || list.isEmpty()) {
            return 0;
        }

        String sql = """
                UPDATE
                    "CERTIFICATE"
                SET
                    QUALIFY_NAME= ?, ACQU_DATE= ?, ORGANIZE_NAME= ?
                WHERE
                    CERT_SEQ= ?
                """;


        int[][] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                list,
                list.size(),
                (PreparedStatement ps, CertificateVo vo) -> {
                    ps.setString(1, vo.getQualifyName());
                    ps.setDate(2, java.sql.Date.valueOf(vo.getAcquDate()));
                    ps.setString(3, vo.getOrganizeName());
                    ps.setLong(4, vo.getCertSeq());
                }
        );

        return batchReturn(updateCounts);
    }

    @Override
    public int batchDelete(List<Long> certSeqList) {
        if (certSeqList == null || certSeqList.isEmpty()) {
            return 0;
        }

        String sql = """
                DELETE FROM "CERTIFICATE" WHERE "CERT_SEQ" = ?
                """;
        int[][] deleteCount = jdbcTemplate.batchUpdate(
                sql,
                certSeqList,
                certSeqList.size(),
                (PreparedStatement ps, Long certSeq) -> ps.setLong(1, certSeq)
        );

        return batchReturn(deleteCount);
    }
}
