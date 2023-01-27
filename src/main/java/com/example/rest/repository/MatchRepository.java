package com.example.rest.repository;

import com.example.rest.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByUserId(Long id);
}
