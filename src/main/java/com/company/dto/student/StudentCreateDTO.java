package com.company.dto.student;

import com.company.enums.StudentGender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentCreateDTO {
    private Integer id;
    private String firstName;
    private String surName;
    private String middleName;
    private String description;
    private StudentGender gender;
    private Integer studyFieldId;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private LocalDate birthDate;
    private LocalDateTime createdTime = LocalDateTime.now();
}