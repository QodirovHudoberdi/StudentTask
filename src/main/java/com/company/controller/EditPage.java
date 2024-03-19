package com.company.controller;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.UniversityDTO;
import com.company.service.EditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edit")
public class EditPage {
    @Autowired
    private EditServiceImpl editService;

    @PutMapping("/university/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UniversityDTO universityDTO) {
        editService.updateUniversityName(universityDTO, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/fieldStudies/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FieldStudiesDTO dto) {
        editService.updateFieldName(id, dto);
        return ResponseEntity.ok().body("Successfully updated Field name of studies  ");
    }
}
