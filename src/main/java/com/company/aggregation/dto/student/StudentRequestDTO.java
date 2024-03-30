package com.company.aggregation.dto.student;

import com.company.aggregation.enums.StudentGender;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentRequestDTO {
    private Integer id;
    @Size(min = 2,max =30)
    private String firstName;
    @Size(min = 2,max =30)
    private String surName;
    @Size(min = 2,max =30)
    private String middleName;
    private String description;
    private StudentGender gender;
    private Integer studyFieldId;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private LocalDate birthDate;
    private LocalDateTime createdTime = LocalDateTime.now();
}