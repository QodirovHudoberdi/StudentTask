package com.company.aggregation.dto.student;

import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.aggregation.enums.StudentGender;
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
    private FieldStudiesDto studyField;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private LocalDate birthDate;
    private LocalDateTime createdTime = LocalDateTime.now();
}