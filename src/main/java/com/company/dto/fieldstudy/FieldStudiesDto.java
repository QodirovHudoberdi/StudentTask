package com.company.dto.fieldstudy;

import com.company.dto.UniversityDTO;
import lombok.Data;

@Data
public class FieldStudiesDto {
    private Integer id;
    private String name;
    private UniversityDTO university;
}