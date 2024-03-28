package com.company.repository;

import com.company.entity.UniversityEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends PagingAndSortingRepository<UniversityEntity, Integer> {
}