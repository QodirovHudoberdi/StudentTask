package com.company.repository;

import com.company.entity.FieldStudiesEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudyFieldRepository extends CrudRepository<FieldStudiesEntity ,Integer> {
}
