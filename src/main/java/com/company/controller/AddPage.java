package com.company.controller;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.UniversityDTO;
import com.company.service.StudyFieldServiceImpl;
import com.company.service.UniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")

public class AddPage {
    @Autowired
    private UniversityServiceImpl universityInterface;
    @Autowired
    private StudyFieldServiceImpl studyFieldService;

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
        return ResponseEntity.ok(studyFieldService.create(fieldstudiesDTO));
    }

    @GetMapping("/fieldOfStudies")
    public ResponseEntity<?> getList1() {
        return ResponseEntity.ok(studyFieldService.getList1());
    }
}
