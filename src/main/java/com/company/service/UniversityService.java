package com.company.service;

import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import com.company.interfaces.University;
import com.company.repository.UniversityRepository;
import com.company.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService implements University {
   LoggerUtil loggerUtil=new LoggerUtil();


    @Autowired
    private UniversityRepository universityRepository;

    /**
     * Create new university to database
     *
     * @param universityDTO fields of university which must be created
     */
    @Override
    public UniversityDTO create(UniversityDTO universityDTO) {
      //  loggerUtil.initializeLogger();
        UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setName(universityDTO.getName());
        universityRepository.save(universityEntity);
        universityDTO.setId(universityEntity.getId());
        loggerUtil.logInfo("Create new University");
        return universityDTO;
    }
    /**
     * Get list of universities
     */
    @Override
    public List<UniversityEntity> getList(Integer pageNo, Integer pageSize) {
    loggerUtil.initializeLogger();
        Pageable page= PageRequest.of(pageNo,pageSize, Sort.by("id"));
        Page<UniversityEntity> page1=universityRepository.findAll(page);
        loggerUtil.logInfo("Get list of Universities ");
        //  log.info("Request Get List  {}  of field studies", pageSize);
        if (page1.hasContent()) {
            return page1.getContent();
        }else {
            Pageable page11= PageRequest.of(0,5, Sort.by("createdTime"));
            Page<UniversityEntity> pageDefault=universityRepository.findAll(page11);
            return pageDefault.getContent();
        }

    }
}


