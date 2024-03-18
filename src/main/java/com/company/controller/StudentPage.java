package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.service.ListService;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentPage {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ListService listService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO profileDto) {
        StudentDTO dto = studentService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping("")
    public ResponseEntity<?> getList() {
        List<StudentDTO> dto = studentService.getList();
        listService.getExcel(dto);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id,
                                        @RequestBody String path) {
        StudentDTO dto = studentService.getStudentById(id);
        listService.getPdf(dto, path);

        return ResponseEntity.ok().body(dto);
    }
}
