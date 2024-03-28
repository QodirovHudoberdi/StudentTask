package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.UniversityDTO;
import com.company.dto.student.StudentCreateDTO;
import com.company.dto.student.StudentDto;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.StudentEntity;
import com.company.entity.UniversityEntity;
import com.company.exception.NotFoundException;
import com.company.exception.OkResponse;
import com.company.exception.WrongException;
import com.company.interfaces.Documents;
import com.company.interfaces.Student;
import com.company.repository.StudentRepository;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import com.company.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Slf4j
public class StudentService implements Student {
    LoggerUtil loggerUtil = new LoggerUtil();
    private static final Logger logger = Logger.getLogger("StudentServiceLogging");
    private static final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudyFieldRepository studyFieldRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private Documents documents;

    /**
     * Get List of students to database
     */

    @Override
    public List<StudentEntity> getList(Integer pageNo, Integer pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("createdTime"));
        Page<StudentEntity> page1 = studentRepository.findAll(page);
        //logger.log(Level.INFO, "Creating a new student:");
        loggerUtil.initializeLogger();
        loggerUtil.logInfo("Get list of Students ");
        if (page1.hasContent()) {
            return page1.getContent();
        } else {
            Pageable page11 = PageRequest.of(0, 10, Sort.by("createdTime"));
            Page<StudentEntity> pageDefault = studentRepository.findAll(page11);
            return pageDefault.getContent();
        }
    }

    /**
     * Create student to database
     *
     * @param studentCreateDto fields of student which must be created
     * @return
     */
    @Override
    public StudentDto create(StudentCreateDTO studentCreateDto) {
        logger.log(Level.INFO, "Creating a new student: {0}", studentCreateDto.getFirstName() + " " + studentCreateDto.getSurName());
        Optional<FieldStudiesEntity> studyField = studyFieldRepository.findById(studentCreateDto.getStudyFieldId());
        if (studyField.isEmpty()) {
            throw new WrongException("Field of study enterred wrong");
        }
        FieldStudiesEntity studyfieldEntity = studyField.get();
        Optional<UniversityEntity> university = universityRepository.findById(studyfieldEntity.getUniversityId().getId());
        UniversityEntity universityEntity = university.get();
        StudentEntity entity = toStudentEntity(studentCreateDto);
        studentRepository.save(entity);
        StudentDto studentDto = toStudentDto(entity);
        studentDto.getStudyFieldId().setName(studyfieldEntity.getName());
        studentDto.getStudyFieldId().setUniversity(new UniversityDTO(universityEntity.getId(), universityEntity.getName()));
        return studentDto;
    }

    /**
     * Get student from database
     *
     * @param id    id of student which must be search
     * @param photo base64's student profile photo
     */
    @Override
    public StudentDto getStudentById(Integer id, PhotoDTO photo) {
        logger.log(Level.INFO, "Getting student by ID: {0}", id);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("User Not Found ");

        StudentEntity studentEntity = byId.get();
        StudentDto studentDto = toStudentDto(studentEntity);
        documents.getPdf(studentDto, photo);
        return toStudentDto(studentEntity);

    }

    /**
     * Delete student from database
     *
     * @param id id of student which must be deleted
     */
    @Override
    public void delete(Integer id) {
        logger.log(Level.INFO, "Deleting student with ID: {0}", id);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            logger.log(Level.WARNING, "NOT Delete  student ");
            throw new NotFoundException("Student Not Found");
        }
        studentRepository.delete(byId.get());
        throw new OkResponse("Deleted");

    }

    /**
     * Upload student's data
     *
     * @param studentCreateDTO new fields of students
     * @param id               id of student which must be change
     */
    @Override
    public StudentDto updateStudent(Integer id, StudentCreateDTO studentCreateDTO) {
        logger.log(Level.INFO, "Updating student with ID: {0}", id);
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("That student is not Found");

        StudentEntity studentEntity = byId.get();
        StudentEntity updateStudent = toUpdateStudentDto(studentEntity, studentCreateDTO);
        studentRepository.save(updateStudent);
        return toStudentDto(updateStudent);

    }

    @Override
    public void getList1() {
        documents.getExcel(studentRepository.findAll());
        throw new OkResponse("Excel File Is Successfully create");
    }

    public String orElse(String value, String other) {
        return value != null ? value : other;
    }

    public LocalDate elseNull(LocalDate value, LocalDate other) {
        return value != null ? value : other;
    }


    public static StudentEntity toStudentEntity(StudentCreateDTO studentCreateDto) {
        return modelMapper.map(studentCreateDto, StudentEntity.class);
    }

    public static StudentDto toStudentDto(StudentEntity studenEntity) {
        return modelMapper.map(studenEntity, StudentDto.class);
    }

    private StudentEntity toUpdateStudentDto(StudentEntity studentEntity, StudentCreateDTO studentCreateDTO) {
        studentEntity.setFirstName(orElse(studentCreateDTO.getFirstName(), studentEntity.getFirstName()));
        studentEntity.setSurName(orElse(studentCreateDTO.getSurName(), studentEntity.getSurName()));
        studentEntity.setMiddleName(orElse(studentCreateDTO.getMiddleName(), studentEntity.getMiddleName()));
        studentEntity.setDescription(orElse(studentCreateDTO.getDescription(), studentEntity.getDescription()));
        studentEntity.setBirthDate(elseNull(studentCreateDTO.getBirthDate(), studentEntity.getBirthDate()));
        studentEntity.setStudyStartDate(elseNull(studentCreateDTO.getStudyStartDate(), studentEntity.getStudyStartDate()));
        studentEntity.setStudyEndDate(elseNull(studentCreateDTO.getStudyEndDate(), studentEntity.getStudyEndDate()));
        studentEntity.setStudyFieldId(studentCreateDTO.getStudyFieldId() == null ? studentEntity.getStudyFieldId() :
                studyFieldRepository.findById(studentCreateDTO.getStudyFieldId()).get());
        studentEntity.setGender(studentCreateDTO.getGender() == null ? studentEntity.getGender() : studentCreateDTO.getGender());
        return studentEntity;
    }
}