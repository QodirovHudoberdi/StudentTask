package com.company.controller;

import com.company.dto.PhotoDTO;
import com.company.dto.student.StudentCreateDTO;
import com.company.dto.student.StudentDto;
import com.company.interfaces.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(student.updateStudent(id, dto));
    }

    @GetMapping("/pageNo={no}/pageSize={size}")
    public ResponseEntity<?> getList(@PathVariable("no") Integer no, @PathVariable("size") Integer size) {
        return ResponseEntity.ok(student.getList(no, size));
    }
    @GetMapping("getExcel")
    public ResponseEntity<?> getList() {
        student.getList1();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id) {
        student.delete(id);
        return ResponseEntity.ok().build();
    }
}