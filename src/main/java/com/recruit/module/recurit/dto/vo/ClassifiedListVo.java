package com.recruit.module.recurit.dto.vo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ClassifiedListVo<T>(
        List<T> insertList, List<T> updateList, List<Long> deleteSeqList
) {
    public static <T extends SequentialVo> ClassifiedListVo<T> getClassifiedList(
            List<T> oldList,
            List<T> newList
    ) {
        Set<Long> oldSeq = Stream.ofNullable(oldList)
                .flatMap(Collection::stream)
                .map(SequentialVo::getSequence)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Long> newSeq = Stream.ofNullable(newList)
                .flatMap(Collection::stream)
                .map(SequentialVo::getSequence)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<T> insertList = Stream.ofNullable(newList)
                .flatMap(Collection::stream)
                .filter(vo -> vo.getSequence() == null)
                .toList();

        List<T> updateList = Stream.ofNullable(newList)
                .flatMap(Collection::stream)
                .filter(vo -> vo.getSequence() != null)
                .filter(vo -> oldSeq.contains(vo.getSequence()))
                .toList();


        List<Long> deleteSeqList = Stream.ofNullable(oldList)
                .flatMap(Collection::stream)
                .map(SequentialVo::getSequence)
                .filter(sequence -> !newSeq.contains(sequence))
                .toList();

        return new ClassifiedListVo<>(insertList, updateList, deleteSeqList);
    }
}
