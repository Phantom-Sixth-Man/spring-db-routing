package com.optum.poc.springdbrouting.controller;


import com.optum.poc.springdbrouting.model.Student;
import com.optum.poc.springdbrouting.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "student")
    public ResponseEntity<List<Student>> getStudents(@RequestHeader(value = "branch",defaultValue = "dev",required = false) String branch) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentService.getStudents());
    }
}
