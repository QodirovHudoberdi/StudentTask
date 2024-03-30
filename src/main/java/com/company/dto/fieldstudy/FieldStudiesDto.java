package com.company.dto.fieldstudy;

import com.company.dto.UniversityDTO;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class FieldStudiesDto {
    private Integer id;
    @Size(min = 2,max =30)
    private String name;
    private UniversityDTO university;
}