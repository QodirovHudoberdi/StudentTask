package com.company.service;

import com.company.aggregation.config.NetworkDataService;
import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.aggregation.entity.FieldStudiesEntity;
import com.company.aggregation.entity.UniversityEntity;
import com.company.exception.NotFoundException;
import com.company.exception.OkResponse;
import com.company.exception.WrongException;
import com.company.service.interfaces.Update;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateService implements Update {

    private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);
    private final NetworkDataService networkDataService;

    private final UniversityRepository universityRepository;

    private final StudyFieldRepository studyFieldRepository;

    /**
     * Update university Name
     *
     * @param universityDTO is value's University
     */
    @Override
    public void updateUniversityName(UniversityDTO universityDTO, Integer id, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<UniversityEntity> byId = universityRepository.findById(id);
        if (byId.isEmpty()) {
            LOG.warn("Not found  University   \t\t {}", (universityDTO));
            throw new NotFoundException("University  Not Found ");
        }
        if (byId.get().getName().equals(universityDTO.getName())) {
            LOG.warn("It is old name of   University   \t\t {}", (universityDTO));
            throw new WrongException("It is Old University Name");
        }
        LOG.warn("Update University   \t\t {}", (universityDTO));
        byId.get().setName(universityDTO.getName());
        universityRepository.save(byId.get());
        throw new OkResponse("University Name have Updated");
    }

    /**
     * Update Field Study Name
     *
     * @param dto is value's fieldStudios
     */
    @Override
    public void updateFieldName(Integer id, FieldStudiesDto dto, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<FieldStudiesEntity> byId = studyFieldRepository.findById(id);
        if (byId.isEmpty()) {
            LOG.warn("Not found  Field of study   \t\t {}", (dto));

            throw new NotFoundException("Field Of Study  Not Found ");
        }
        if (byId.get().getName().equals(dto.getName())) {
            LOG.warn("  It is Old Field Name of Study \t\t {}", (dto));

            throw new WrongException("It is Old Field Name of Study");
        }

        LOG.warn("Update  Field of study   \t\t {}", (dto));
        byId.get().setName(dto.getName());
        studyFieldRepository.save(byId.get());
        throw new OkResponse("Field Name was updated ");
    }
}