package com.company.dto.studen;

import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.enums.StudentGender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDto {
    private Integer id;
    private String firstName;
    private String surName;
    private String middleName;
    private String description;
    private StudentGender gender;
    private FieldStudiesDto studyFieldId;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private LocalDate birthDate;
    private LocalDateTime createdTime = LocalDateTime.now();
}