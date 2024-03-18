package com.company.entity;

import com.company.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="Student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String middle_name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentGender gender;

    @Column(nullable = false , name = "study_field_id")
    private Integer studyFieldId;

    @Column(nullable = false, name = "study_start_date")
    private LocalDateTime studyStartDate;

    @Column(nullable = false, name = "study_end_date")
    private LocalDateTime studyEndDate;

     @Column(nullable = false, name = "birth_date")
    private LocalDateTime birthDate;

     @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();


}
