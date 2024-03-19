package com.company.dto;

import com.company.enums.StudentGender;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Data
public class StudentDTO {


    private Integer id;
    private String firstName;
    private String surName;
    private String middleName;
    private String description;
    private StudentGender gender;
    private LocalDateTime createdTime;
    private String studyStartDate;
    private String studyEndDate;
    private String birthdate;
    private Integer studyFieldId;


}