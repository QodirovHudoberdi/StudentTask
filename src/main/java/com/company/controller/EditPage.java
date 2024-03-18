package com.company.controller;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.StudentDTO;
import com.company.dto.UniversityDTO;
import com.company.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("edit")
public class EditPage {
    @Autowired
    private EditService editService;

    @PutMapping("/student/StartedDate")
    public ResponseEntity<?> update(@RequestBody StudentDTO dto) {
        editService.updateSTD(dto);
        return ResponseEntity.ok().body("Successfully updated started Date ");
    }

    @PutMapping("/student/Surname/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody String surname) {
        editService.updateSurname(surname, id);
        return ResponseEntity.ok().body("Successfully updated Surname  ");
    }

    @PutMapping("/university/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody UniversityDTO universityDTO) {
        editService.updateUniversityName(universityDTO, id);
        return ResponseEntity.ok().body("Successfully updated University Name ");
    }

    @PutMapping("/fieldStudies/name/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody FieldStudiesDTO dto) {
        editService.updateFieldName(id, dto);
        return ResponseEntity.ok().body("Successfully updated Field name of studies  ");
    }
}
