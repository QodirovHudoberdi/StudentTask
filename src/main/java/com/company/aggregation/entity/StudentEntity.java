package com.company.aggregation.entity;

import com.company.aggregation.enums.StudentGender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surName;
    @Column(nullable = false)
    private String middleName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentGender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_field_id")
    private FieldStudiesEntity studyField;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, name = "study_start_date")
    private LocalDate studyStartDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, name = "study_end_date")
    private LocalDate studyEndDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;
    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdTime = LocalDateTime.now();
}