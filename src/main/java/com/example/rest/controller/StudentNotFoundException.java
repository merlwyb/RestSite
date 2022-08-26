package com.example.rest.controller;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(Long id) {
        super("Не был найден сотрудник с id " + id);
    }
}
