package com.botshelo.student_management_system.controller;

import com.botshelo.student_management_system.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import com.botshelo.student_management_system.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.botshelo.student_management_system.dto.StudentDto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public String showAddStudentForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
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

         StudentDto studentDto = new StudentDto(
            student.getName(),
            student.getSurname(),
            student.getEmail()
    );

            model.addAttribute("studentDto", studentDto);
            model.addAttribute("studentId", student.getId());

        return "edit-student";
    }

    // @PostMapping("/admin/students")
    // public String saveStudent(
    //         Long id,
    //         String name,
    //         String surname,
    //         String email) {

    //     Student student;

    //     if (id != null) {
    //         student = studentRepository.findById(id)
    //                 .orElseThrow();
    //     } else {
    //         student = new Student();
    //     }

    //     student.setName(name);
    //     student.setSurname(surname);
    //     student.setEmail(email);

    //     studentRepository.save(student);

    //     return "redirect:/admin/students";

    //     }
    @PostMapping("/admin/students")
    public String saveStudent(
            @Valid @ModelAttribute("studentDto") StudentDto studentDto,
            BindingResult bindingResult,
            Long id,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDto);
            return id != null ? "edit-student" : "add-student";
        }

        Student student;

        if (id != null) {
            student = studentRepository.findById(id)
                    .orElseThrow();
        } else {
            student = new Student();
        }

        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setEmail(studentDto.getEmail());

        studentRepository.save(student);

        return "redirect:/admin/students";
}

    @PostMapping("/admin/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "redirect:/admin/students";
        }
}