package com.recruit.module.recurit.repository.custom;

import com.recruit.module.recurit.dto.request.EducationRequestDto;

import java.util.List;

public interface EducationCustomRepository {
    int batchInsert(List<EducationRequestDto> educationList, Long recruitSeq);
    int batchUpdate(List<EducationRequestDto> educationList);
    int batchDelete(List<Long> eduSeqList);
}
