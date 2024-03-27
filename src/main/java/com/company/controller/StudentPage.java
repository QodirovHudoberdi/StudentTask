package com.company.controller;

import com.company.dto.PhotoDTO;
import com.company.dto.studen.StudentCreateDTO;
import com.company.dto.studen.StudentDto;
import com.company.interfaces.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentPage {
    @Autowired
    private Student student;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCreateDTO profileDto) {
        return ResponseEntity.ok(student.create(profileDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> postImage(
            @PathVariable("id") Integer id,
            @RequestBody PhotoDTO photo
    ) {
        StudentDto dto = student.getStudentById(id, photo);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestBody StudentCreateDTO dto
    ) {
        student.updateStudent(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getList() {
        List<StudentDto> list = student.getList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id) {
        student.delete(id);
        return ResponseEntity.ok().build();
    }
}