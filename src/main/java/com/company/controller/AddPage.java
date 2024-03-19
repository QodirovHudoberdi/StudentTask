package com.company.controller;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.UniversityDTO;
import com.company.service.StudyFieldService;
import com.company.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("create")

public class AddPage {

    @Autowired
    private UniversityService universityService;


    @Autowired
    private StudyFieldService studyFieldService;


    @PostMapping("/university")
    public ResponseEntity<?> create(@RequestBody UniversityDTO universityDTO) {
        UniversityDTO dto = universityService.create(universityDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/studyField")
    public ResponseEntity<?> create(@RequestBody FieldStudiesDTO fieldstudiesDTO) {
        FieldStudiesDTO dto = studyFieldService.create(fieldstudiesDTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("universities")
    public ResponseEntity<?> getList() {
        List<UniversityDTO> dto = universityService.getList();


        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("fieldOfStudies")
    public ResponseEntity<?> getList1() {
        List<FieldStudiesDTO> dto = studyFieldService.getList1();


        return ResponseEntity.ok().body(dto);
    }
}
