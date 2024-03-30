package com.company.controller;

import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.service.interfaces.StudyField;
import com.company.service.interfaces.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/create")

public class AddPage {
    @Autowired
    private University universityInterface;
    @Autowired
    private StudyField studyField;

    @PostMapping("/university")
    public ResponseEntity<?> create(@RequestBody UniversityDTO universityDTO,HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(universityInterface.create(universityDTO,httpServletRequest));
    }

    @GetMapping("/universities/pageNo={no}/pageSize={size}")
    public ResponseEntity<?> getList(@PathVariable("no") Integer no, @PathVariable("size") Integer size,HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(universityInterface.getList(no, size,httpServletRequest));
    }

    @PostMapping("/studyField")
    public ResponseEntity<?> create(@RequestBody FieldStudiesRequestDTO fieldstudiesRequestDTO, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(studyField.create(fieldstudiesRequestDTO,httpServletRequest));
    }

    @GetMapping("/fieldOfStudies/pageNo={no}/pageSize={size}")
    public ResponseEntity<?> getList1(@PathVariable("no") Integer no, @PathVariable("size") Integer size,HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(studyField.getList(no, size,httpServletRequest));
    }
}