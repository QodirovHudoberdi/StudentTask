package com.company.service;

import com.company.NetworkDataService;
import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import com.company.interfaces.University;
import com.company.mapper.UniversityMapper;
import com.company.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService implements University {


    private final UniversityMapper universityMapper;
    private final UniversityRepository universityRepository;
    private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);
    private final NetworkDataService networkDataService;


    /**
     * Create new university to database
     *
     * @param universityDTO fields of university which must be created
     */
    @Override
    public UniversityDTO create(UniversityDTO universityDTO, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);

        UniversityEntity universityEntity = universityMapper.toEntity(universityDTO);
        universityRepository.save(universityEntity);
        LOG.info("create University   \t\t {}", universityDTO);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        return universityMapper.toDto(universityEntity);
    }

    /**
     * Get list of universities
     * @param pageNo page number
     * @param pageSize count of Data
     */
    @Override
    public List<UniversityDTO> getList(Integer pageNo, Integer pageSize, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);

        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<UniversityEntity> page1 = universityRepository.findAll(page);
        LOG.info("Get List of Universities   \t\t {}", pageSize);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        return universityMapper.toDto(page1.getContent());

    }
}