package com.example.students;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementApplication {
    public static void main(String[] args) { SpringApplication.run(StudentManagementApplication.class, args); }
    @Bean CommandLineRunner seed(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Student("Aarav Sharma", "aarav@example.com", "Computer Science", 88.5));
                repository.save(new Student("Diya Rao", "diya@example.com", "Information Technology", 92.0));
                repository.save(new Student("Kabir Singh", "kabir@example.com", "Cybersecurity", 76.0));
            }
        };
    }
}
