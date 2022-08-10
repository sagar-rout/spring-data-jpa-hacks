package com.example.demospringdatajpa.controller;

import com.example.demospringdatajpa.domain.Student;
import com.example.demospringdatajpa.repo.StudentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/students", produces = APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
