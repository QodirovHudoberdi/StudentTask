package com.company.controller;

import com.company.interfaces.Document;
import com.company.interfaces.Student;
import com.company.models.PhotoDTO;
import com.company.models.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentPage {
    @Autowired
    private Student student;
    @Autowired
    private Document document;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO profileDto) {
        return ResponseEntity.ok(student.create(profileDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postImage(
            @PathVariable("id") Integer id,
            @RequestBody PhotoDTO photo
    ) {
        StudentDTO dto = student.getStudentById(id);
        document.getPdf(dto, photo);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestBody StudentDTO dto
    ) {
        student.updateStudent(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getList() {
        List<StudentDTO> list = student.getList();
        document.getExcel(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(student.getStudentById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id) {
        student.delete(id);
        return ResponseEntity.ok().build();
    }
}