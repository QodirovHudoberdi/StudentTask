package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.UniversityDTO;
import com.company.dto.studen.StudentCreateDTO;
import com.company.dto.studen.StudentDto;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements Student {

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
    public List<StudentDto> getList() {
        Iterable<StudentEntity> all = studentRepository.findAll();
        List<StudentDto> dtoList = new LinkedList<>();

        all.forEach(listEntity -> {
            StudentDto dto = toStudentDto(listEntity);
            dtoList.add(dto);
        });
        documents.getExcel(dtoList);

        return dtoList;
    }

    /**
     * Create student to database
     *
     * @param studentCreateDto fields of student which must be created
     * @return
     */
    @Override
    public StudentDto create(StudentCreateDTO studentCreateDto) {
        Optional<FieldStudiesEntity> studyField = studyFieldRepository.findById(studentCreateDto.getStudyFieldId());
        if (studyField.isEmpty()) {
            throw new WrongException("Field of study enterred wrong");
        }
        FieldStudiesEntity studyfieldEntity = studyField.get();
        Optional<UniversityEntity> university = universityRepository.findById(studyfieldEntity.getUniversityId().getId());
        UniversityEntity universityEntity = university.get();
        StudentEntity entity = toStudentDto(studentCreateDto);
        studentRepository.save(entity);
        StudentDto studentDto = toStudentDto(entity);
        studentDto.getStudyFieldId().setName(studyfieldEntity.getName());
        studentDto.getStudyFieldId().setUniversity(new UniversityDTO(universityEntity.getId(),universityEntity.getName()));
        return studentDto;
    }

    /**
     * Get student from database
     *
     * @param id    id of student which must be search
     * @param photo  base64's student profile photo
     */
    @Override
    public StudentDto getStudentById(Integer id, PhotoDTO photo) {
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
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("Student Not Found");

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
    public void updateStudent(Integer id, StudentCreateDTO studentCreateDTO) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) throw new NotFoundException("That student is not Found");

        StudentEntity studentEntity = byId.get();
        StudentEntity updateStudent = toUpdateStudentDto(studentEntity, studentCreateDTO);
        studentRepository.save(updateStudent);
        studentCreateDTO.setId(studentEntity.getId());
        throw new OkResponse("Student Updated");
    }

    public String orElse(String value, String other) {
        return value != null ? value : other;
    }

    public LocalDate elseNull(LocalDate value, LocalDate other) {
        return value != null ? value : other;
    }


    public static StudentEntity toStudentDto(StudentCreateDTO studentCreateDto) {
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