package com.example.rest.service.impl;

import com.example.rest.entity.Match;
import com.example.rest.repository.MatchRepository;
import com.example.rest.service.MatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    @Transactional
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional
    public List<Match> getAllByUserId(Long userId) {
        return matchRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    @Override
    @Transactional
    public Match findById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with -> id: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }
}
