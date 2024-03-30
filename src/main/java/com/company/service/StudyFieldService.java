package com.company.service;

import com.company.NetworkDataService;
import com.company.dto.UniversityDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.UniversityEntity;
import com.company.exception.WrongException;
import com.company.interfaces.StudyField;
import com.company.mapper.FieldStudyMapper;
import com.company.mapper.UniversityMapper;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudyFieldService implements StudyField {
    private final StudyFieldRepository studyFieldRepository;

    private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);
    private final NetworkDataService networkDataService;
    private final Gson gson;
    private final UniversityRepository universityRepository;
    private final FieldStudyMapper fieldStudyMapper;
    private final UniversityMapper universityMapper;


    /**
     * Create Field Study
     *
     * @param fieldStudiesRequestDto is value's of new fieldStudios
     */
    @Override
    public FieldStudiesDto create(FieldStudiesRequestDTO fieldStudiesRequestDto, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);

        Optional<UniversityEntity> universityEntity = universityRepository.findById(fieldStudiesRequestDto.getUniversityId());
        if (universityEntity.isEmpty()) {
            LOG.info("create studyField   \t\t {}", (fieldStudiesRequestDto));
            LOG.info("Client host : \t\t {}", ClientInfo);
            LOG.info("Client IP :  \t\t {}", ClientIP);
            throw new WrongException("University Not Found");
        }

        UniversityDTO universityDTO = universityMapper.toDto(universityEntity.get());
        LOG.info("create studyField   \t\t {}", gson.toJson(fieldStudiesRequestDto));
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        FieldStudiesEntity entity = fieldStudyMapper.toEntity(fieldStudiesRequestDto);
        studyFieldRepository.save(entity);
        FieldStudiesDto fieldStudiesDto = fieldStudyMapper.toDto(entity);
        fieldStudiesDto.setUniversity(universityDTO);
        fieldStudiesDto.setUniversity(universityDTO);
        return fieldStudiesDto;
    }

    /**
     * Get list of  field of studies
     */

    public List<FieldStudiesDto> getList(Integer pageNo, Integer pageSize, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Get studyField  \t\t {}", pageSize);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<FieldStudiesEntity> page1 = studyFieldRepository.findAll(page);
        if (page1.hasContent()) {
            return fieldStudyMapper.toDto(page1);
        }
        return fieldStudyMapper.toDto(studyFieldRepository.findAll());
    }
}