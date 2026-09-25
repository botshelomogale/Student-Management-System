package com.botshelo.student_management_system.config;

import com.botshelo.student_management_system.model.Role;
import com.botshelo.student_management_system.model.User;
import com.botshelo.student_management_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDataInitializer {

    @Bean
    CommandLineRunner initUsers(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {
        return args -> {

            User admin = userRepository.findByUsername("admin")
                    .orElseGet(() -> new User());

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            
        };
    }
}