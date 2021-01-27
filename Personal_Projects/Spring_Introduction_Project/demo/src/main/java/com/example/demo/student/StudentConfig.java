package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
                Student blake = new Student(
                        "Blake",
                        "blake.rusteberg@gmail.com",
                        LocalDate.of(2000, Month.DECEMBER, 5),
                        21

                );

                Student alex = new Student(
                        "alex",
                        "alex.johnson@gmail.com",
                        LocalDate.of(2009, Month.JANUARY, 23),
                        25
                );

                repository.saveAll(
                        List.of(blake,alex)
            );

        };
    }

}
