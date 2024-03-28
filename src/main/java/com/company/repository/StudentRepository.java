package com.company.repository;

import com.company.entity.StudentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Integer> {
    List<StudentEntity> findAll();

    Optional<StudentEntity> findById(Integer id);
}