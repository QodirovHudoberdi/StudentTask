package com.company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FieldOfStudies")
public class FieldStudiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private UniversityEntity university;

}