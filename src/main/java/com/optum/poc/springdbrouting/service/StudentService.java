package com.optum.poc.springdbrouting.service;


import com.optum.poc.springdbrouting.model.Student;
import com.optum.poc.springdbrouting.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
