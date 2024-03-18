package com.company.repository;

import com.company.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    Iterable<StudentEntity> findAll();

    Optional<StudentEntity> findById(Integer id);


}
