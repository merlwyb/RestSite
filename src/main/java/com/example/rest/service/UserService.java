package com.example.rest.service;

import com.example.rest.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User findByLogin(String login);

    User save(User user);

    User findById(Long id);

    void deleteById(Long id);
}
