package com.company.aggregation.dto;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {
    private Integer id;
    @Size(min = 2,max =30)
    private String name;
}