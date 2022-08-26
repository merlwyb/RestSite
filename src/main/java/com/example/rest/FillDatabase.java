package com.example.rest;

import com.example.rest.entity.Student;
import com.example.rest.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FillDatabase {
//    private static final Logger log = LoggerFactory.getLogger(FillDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(StudentRepository studentRepository) {
//        return args -> {
//            log.info("Preloading " + studentRepository.save(new Student("Bilbo Baggins", "FPD-23-23")));
//            log.info("Preloading " + studentRepository.save(new Student("Frodo Baggins", "EID-19-27")));
//        };
//    }
//}
