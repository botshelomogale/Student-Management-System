package com.botshelo.student_management_system.config;

import com.botshelo.student_management_system.model.Student;
import com.botshelo.student_management_system.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {
        return args -> {

            if (studentRepository.count() == 0) {

                Student student = new Student(
                        null,
                        "Botshelo",
                        "Mogale",
                        "botshelo@example.com"
                );

                studentRepository.save(student);
            }
        };
    }
}