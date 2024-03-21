package com.company.controller;

import com.company.models.FieldStudiesDTO;
import com.company.models.UniversityDTO;
import com.company.interfaces.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edit")
public class EditPage {
    @Autowired
    private Update update;

    @PutMapping("/university/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UniversityDTO universityDTO) {
        update.updateUniversityName(universityDTO, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/fieldStudies/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FieldStudiesDTO dto) {
        update.updateFieldName(id, dto);
        return ResponseEntity.ok().body("Successfully updated Field name of studies  ");
    }
}