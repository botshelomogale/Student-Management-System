package com.botshelo.student_management_system.controller;

import com.botshelo.student_management_system.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import com.botshelo.student_management_system.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/profile")
    public String getStudentProfile(Model model) {

        Student student = studentRepository.findById(1L)
                        .orElseThrow();

        model.addAttribute("student", student);

        return "student-profile";
    }
    @GetMapping("/admin/students/add")
    public String showAddStudentForm() {
        return "add-student";
    }
    @GetMapping("/admin/students")
    public String listStudents(Model model) {

        model.addAttribute("students", studentRepository.findAll());

        return "student-list";
}

    @PostMapping("/admin/students")
    public String addStudent(
            String name,
            String surname,
            String email) {

        Student student = new Student(
                null,
                name,
                surname,
                email
        );

        studentRepository.save(student);

        return "redirect:/admin/students/add";
}
}