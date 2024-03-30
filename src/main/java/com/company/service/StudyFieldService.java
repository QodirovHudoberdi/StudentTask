package com.company.service;

import com.company.aggregation.config.NetworkDataService;
import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.aggregation.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.aggregation.entity.FieldStudiesEntity;
import com.company.aggregation.entity.UniversityEntity;
import com.company.exception.WrongException;
import com.company.service.interfaces.StudyField;
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
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<UniversityEntity> universityEntity = universityRepository.findById(fieldStudiesRequestDto
                .getUniversityId());
        if (universityEntity.isEmpty()) {
            LOG.info("create studyField   \t\t {}", (fieldStudiesRequestDto));
            throw new WrongException("University Not Found");
        }

        UniversityDTO universityDTO = universityMapper.toDto(universityEntity.get());
        LOG.info("create studyField   \t\t {}", gson.toJson(fieldStudiesRequestDto));
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
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        LOG.info("Get studyField  \t\t {}", pageSize);
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<FieldStudiesEntity> page1 = studyFieldRepository.findAll(page);
        if (page1.hasContent()) {
            return fieldStudyMapper.toDto(page1);
        }
        return fieldStudyMapper.toDto(studyFieldRepository.findAll());
    }
}