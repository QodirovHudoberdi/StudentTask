package com.company.service;

import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import com.company.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UniversityService implements UniversityServiceImpl {
    @Autowired
    private UniversityRepository universityRepository;

    //Create University
    @Override
    public UniversityDTO create(UniversityDTO universityDTO) {
        UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setName(universityDTO.getName());
        universityRepository.save(universityEntity);
        universityDTO.setId(universityEntity.getId());
        return universityDTO;


    }

    //Get List of  Universities
    @Override
    public List<UniversityDTO> getList() {
        Iterable<UniversityEntity> all = universityRepository.findAll();
        List<UniversityDTO> dtolist = new LinkedList<>();
        all.forEach(listEntity -> {
            UniversityDTO dto = new UniversityDTO();
            dto.setId(listEntity.getId());
            dto.setName(listEntity.getName());
            dtolist.add(dto);

        });
        return dtolist;


    }
}