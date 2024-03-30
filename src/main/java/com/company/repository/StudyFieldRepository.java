package com.company.repository;

import com.company.entity.FieldStudiesEntity;
import com.company.entity.StudentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudyFieldRepository extends PagingAndSortingRepository<FieldStudiesEntity, Integer> {
    List<FieldStudiesEntity> findAll();
}