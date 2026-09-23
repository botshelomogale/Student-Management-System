package com.botshelo.student_management_system.controller;

import com.botshelo.student_management_system.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import com.botshelo.student_management_system.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/admin/students/{id}/edit")
    public String showEditStudentForm(
        @PathVariable Long id,
        Model model) {

        Student student = studentRepository.findById(id)
            .orElseThrow();

        model.addAttribute("student", student);

        return "edit-student";
    }

@PostMapping("/admin/students")
public String saveStudent(
        Long id,
        String name,
        String surname,
        String email) {

    Student student;

    if (id != null) {
        student = studentRepository.findById(id)
                .orElseThrow();
    } else {
        student = new Student();
    }

    student.setName(name);
    student.setSurname(surname);
    student.setEmail(email);

    studentRepository.save(student);

    return "redirect:/admin/students";

    }

@PostMapping("/admin/students/{id}/delete")
public String deleteStudent(@PathVariable Long id) {

    studentRepository.deleteById(id);

    return "redirect:/admin/students";
    }
}