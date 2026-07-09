package com.recruit.module.recurit.dto.vo;

import java.util.List;

public record ClassifiedListVo<T>(
        List<T> insertList, List<T> updateList, List<T> deleteList
) {
}
