package com.company.controller;

import com.company.models.FieldStudiesDTO;
import com.company.models.UniversityDTO;
import com.company.interfaces.StudyField;
import com.company.interfaces.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")

public class AddPage {
    @Autowired
    private University universityInterface;
    @Autowired
    private StudyField studyField;

    @PostMapping("/university")
    public ResponseEntity<?> create(@RequestBody UniversityDTO universityDTO) {
        return ResponseEntity.ok(universityInterface.create(universityDTO));
    }

    @GetMapping("/universities")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(universityInterface.getList());
    }

    @PostMapping("/studyField")
    public ResponseEntity<?> create(@RequestBody FieldStudiesDTO fieldstudiesDTO) {
        return ResponseEntity.ok(studyField.create(fieldstudiesDTO));
    }

    @GetMapping("/fieldOfStudies")
    public ResponseEntity<?> getList1() {
        return ResponseEntity.ok(studyField.getList1());
    }
}