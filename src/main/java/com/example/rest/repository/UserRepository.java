package com.example.rest.repository;

import com.example.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginIgnoreCase(String login);

    Optional<User> findById(Long id);
}
