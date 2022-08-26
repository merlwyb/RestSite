package com.example.rest.controller;

import com.example.rest.entity.Student;
import com.example.rest.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    List<Student> all() {
        return studentRepository.findAll();
    }

    @PostMapping
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @GetMapping("/{id}")
    Student findStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PutMapping("/{id}")
    Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setStudentName(newStudent.getStudentName());
                    student.setGroupName(newStudent.getGroupName());
                    return studentRepository.save(student);
                }).orElseGet(() -> {
                    newStudent.setId(id);
                    return studentRepository.save(newStudent);
                });
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

}
