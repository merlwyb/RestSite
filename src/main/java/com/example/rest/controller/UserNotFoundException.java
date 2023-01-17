package com.example.rest.controller;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Не был найден пользователь с id " + id);
    }
}
