package com.company.repository;

import com.company.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EditRepository extends CrudRepository<StudentEntity ,Integer> {
    @Override
    Optional<StudentEntity> findById(Integer integer);
    Optional<StudentEntity> findBySurname(String surname);
}
