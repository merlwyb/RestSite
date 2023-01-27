package com.example.rest.service;

import com.example.rest.entity.Match;

import java.util.List;

public interface MatchService {
    List<Match> getAll();

    List<Match> getAllByUserId(Long userId);

    Match save(Match match);

    Match findById(Long id);

    void deleteById(Long id);
}
