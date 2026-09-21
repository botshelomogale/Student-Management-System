package com.botshelo.student_management_system.controller;

import com.botshelo.student_management_system.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    @GetMapping("/student/profile")
    public String getStudentProfile(Model model) {

        Student student = new Student(
                1L,
                "Botshelo",
                "Mogale",
                "botshelo@example.com"
        );
        model.addAttribute("student", student);

        return "student-profile";
    }
}