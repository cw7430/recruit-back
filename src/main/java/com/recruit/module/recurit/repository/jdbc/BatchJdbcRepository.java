package com.recruit.module.recurit.repository.jdbc;

import java.util.List;

public interface BatchJdbcRepository<T> {
    int batchInsert(List<T> list, Long parentId);
    int batchUpdate(List<T> list);
    int batchDelete(List<Long> idList);
}
