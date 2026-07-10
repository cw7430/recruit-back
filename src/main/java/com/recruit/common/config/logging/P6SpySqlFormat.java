package com.recruit.common.config.logging;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

public class P6SpySqlFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        if (sql == null || sql.trim().isEmpty()) {
            return "";
        }
        return String.format("\n%d ms\n%s", elapsed, FormatStyle.BASIC.getFormatter().format(sql));
    }
}