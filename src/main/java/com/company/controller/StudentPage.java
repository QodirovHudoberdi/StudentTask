package com.company.controller;

import com.company.dto.PhotoDTO;
import com.company.dto.StudentDTO;
import com.company.service.ListService;
import com.company.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentPage {
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private ListService listService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO profileDto) {
        StudentDTO dto = studentServiceImpl.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        studentServiceImpl.updateStudent(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getList() {
        List<StudentDTO> dto = studentServiceImpl.getList();
        listService.getExcel(dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {
        StudentDTO dto = studentServiceImpl.getStudentById(id);


        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postImage(@PathVariable("id") Integer id,
                                       @RequestBody PhotoDTO photo) {
        StudentDTO dto = studentServiceImpl.getStudentById(id);
        listService.getPdf(dto, photo);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id) {
        String dto = studentServiceImpl.delete(id);
        return ResponseEntity.ok().body(dto);
    }
}
