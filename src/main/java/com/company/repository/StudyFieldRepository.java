package com.company.repository;

import com.company.aggregation.entity.FieldStudiesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudyFieldRepository extends PagingAndSortingRepository<FieldStudiesEntity, Integer> {
    List<FieldStudiesEntity> findAll();
}