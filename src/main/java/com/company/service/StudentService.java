package com.company.service;

import com.company.aggregation.config.NetworkDataService;
import com.company.aggregation.dto.PhotoDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.aggregation.dto.student.StudentDto;
import com.company.aggregation.dto.student.StudentRequestDTO;
import com.company.aggregation.entity.FieldStudiesEntity;
import com.company.aggregation.entity.StudentEntity;
import com.company.exception.NotFoundException;
import com.company.exception.OkResponse;
import com.company.exception.WrongException;
import com.company.service.interfaces.Documents;
import com.company.service.interfaces.Student;
import com.company.mapper.FieldStudyMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.StudentRepository;
import com.company.repository.StudyFieldRepository;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService implements Student {

    private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);
    private final NetworkDataService networkDataService;
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final StudyFieldRepository studyFieldRepository;
    private final Documents documents;
    private final FieldStudyMapper fieldStudyMapper;

    /**
     * Get List of students to database
     */

    @Override
    public List<StudentDto> getList(Integer pageNo, Integer pageSize, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);

        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("createdTime"));
        Page<StudentEntity> page1 = studentRepository.findAll(page);
        LOG.info("Get Students List   \t\t {}", page1);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        return studentMapper.toDto(page1);
    }


    /**
     * Create student to database
     *
     * @param studentRequestDto fields of student which must be created
     */
    @Override
    public StudentDto create(StudentRequestDTO studentRequestDto, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        validation(studentRequestDto);
        Optional<FieldStudiesEntity> byId = studyFieldRepository.findById(studentRequestDto.getStudyFieldId());
        if (byId.isEmpty()) {
            LOG.warn("Not study Field : Id not Found   \t\t {}", studentRequestDto.getStudyFieldId());
            throw new NotFoundException("That study Field is not Found");
        }

        StudentEntity studentEntity = studentMapper.toEntity(studentRequestDto);
        studentEntity.setStudyField(byId.get());
        studentRepository.save(studentEntity);

        LOG.info("create student  \t\t {}", studentRequestDto);

        StudentDto dto = studentMapper.toDto(studentEntity);
        dto.setStudyField( fieldStudyMapper.toDto(studentEntity.getStudyField()));
        return dto;

    }

    /**
     * Get student from database
     *
     * @param id    id of student which must be search
     * @param photo base64's student profile photo
     */
    @Override
    public StudentDto getStudentById(Integer id, PhotoDTO photo, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            LOG.warn("Student not Found   \t\t {}", id);
            throw new NotFoundException("User Not Found ");
        }

        StudentEntity studentEntity = byId.get();
        StudentDto studentDto = studentMapper.toDto(studentEntity);
        FieldStudiesDto dto = fieldStudyMapper.toDto(studentEntity.getStudyField());
        studentDto.setStudyField(dto);
        LOG.info("Get one Student by Id   \t\t {}", studentDto);
        documents.getPdf(studentDto, photo);
        return studentDto;
    }

    /**
     * Delete student from database
     *
     * @param id id of student which must be deleted
     */
    @Override
    public void delete(Integer id, HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            LOG.warn("Not Delete student : Id not Found   \t\t {}", id);
            throw new NotFoundException("Student Not Found");
        }
        studentRepository.delete(byId.get());
        LOG.info("Delete Student by Id :   \t\t {}", id);
        throw new OkResponse("Deleted");

    }

    /**
     * Upload student's data
     *
     * @param studentRequestDTO new fields of students
     * @param id                id of student which must be change
     */
    @Override
    public StudentDto updateStudent(Integer id, StudentRequestDTO studentRequestDTO,
                                    HttpServletRequest httpServletRequest) {
        String ClientIP = networkDataService.getClientIPv4Address(httpServletRequest);
        String ClientInfo = networkDataService.getRemoteUserInfo(httpServletRequest);
        LOG.info("Client host : \t\t {}", ClientInfo);
        LOG.info("Client IP :  \t\t {}", ClientIP);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            LOG.warn("Not Update student : Id not Found   \t\t {}", id);
            throw new NotFoundException("That student is not Found");
        }

        StudentEntity studentEntity = byId.get();

        studentRepository.save(studentMapper.updateFromDto(studentRequestDTO, studentEntity));
        LOG.info("Update Student by Id :   \t\t {}", studentEntity);

        return studentMapper.toDto(studentEntity);

    }

    @Override
    public void getList1() {

        documents.getExcel(studentRepository.findAll());
        throw new OkResponse("Excel File Is Successfully create");
    }

    public void validation(StudentRequestDTO studentRequestDTO) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<StudentRequestDTO>> violations = validator.validate(studentRequestDTO);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<StudentRequestDTO> violation : violations) {
                errorMessage.append(violation.getPropertyPath()).append(" Entered wrong ");
            }
            throw new WrongException(errorMessage.toString());
        }
    }
}