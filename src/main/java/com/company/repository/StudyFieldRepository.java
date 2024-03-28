package com.company.repository;

import com.company.entity.FieldStudiesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudyFieldRepository extends PagingAndSortingRepository<FieldStudiesEntity, Integer> {
}