package com.company.repository;

import com.company.entity.UniversityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends CrudRepository<UniversityEntity, Integer> {}