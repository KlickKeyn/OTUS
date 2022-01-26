package com.nigga_security.controller;

import com.nigga_security.dao.domain.Student;
import com.nigga_security.exception.StudentException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {

    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Niggaдяй"),
            new Student(3, "Hell Man")
    );

    @GetMapping
    public List<Student> getAllStudent() {
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(String.format("Registered new student %s", student));
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("Student with id = %s updated to %s", studentId, student));
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(String.format("Deleted student with id = %s", studentId));
    }
}
