package com.company.controller;

import com.company.aggregation.dto.PhotoDTO;
import com.company.aggregation.dto.student.StudentRequestDTO;
import com.company.aggregation.dto.student.StudentDto;
import com.company.service.interfaces.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("student")
public class StudentPage {
    @Autowired
    private Student student;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentRequestDTO profileDto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(student.create(profileDto,httpServletRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> postImage(
            @PathVariable("id") Integer id,
            @RequestBody PhotoDTO photo,
            HttpServletRequest httpServletRequest
   ) {
        StudentDto dto = student.getStudentById(id, photo, httpServletRequest);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @RequestBody StudentRequestDTO dto,
            HttpServletRequest httpServletReques
    ) {

        return ResponseEntity.ok(student.updateStudent(id, dto,httpServletReques));
    }

    @GetMapping("/pageNo={no}/pageSize={size}")
    public ResponseEntity<?> getList(@PathVariable("no") Integer no, @PathVariable("size") Integer size,
                                     HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(student.getList(no, size,httpServletRequest));
    }
    @GetMapping("getExcel")
    public ResponseEntity<?> getList() {
        student.getList1();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id,HttpServletRequest httpServletRequest) {
        student.delete(id,httpServletRequest);
        return ResponseEntity.ok().build();
    }
}