package com.botshelo.student_management_system.controller;

import com.botshelo.student_management_system.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public Student getStudent() {

        return new Student(
                1L,
                "Botshelo",
                "Mogale",
                "botshelo@example.com"
        );
    }
}