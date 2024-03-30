package com.company.controller;

import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.service.interfaces.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/edit")
public class EditPage {
    @Autowired
    private Update update;

    @PutMapping("/university/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UniversityDTO universityDTO, HttpServletRequest httpServletRequest) {
        update.updateUniversityName(universityDTO, id,httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/fieldStudies/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FieldStudiesDto dto,HttpServletRequest httpServletRequest) {
        update.updateFieldName(id, dto,httpServletRequest);
        return ResponseEntity.ok().body("Successfully updated Field name of studies  ");
    }
}