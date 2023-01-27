package com.example.rest.service.impl;

import com.example.rest.entity.Match;
import com.example.rest.entity.User;
import com.example.rest.entity.security.Game;
import com.example.rest.entity.security.GameType;
import com.example.rest.repository.GameRepository;
import com.example.rest.repository.MatchRepository;
import com.example.rest.service.MatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    MatchRepository matchRepository;
    GameRepository gameRepository;

    public MatchServiceImpl(MatchRepository matchRepository, GameRepository gameRepository) {
        this.matchRepository = matchRepository;
        this.gameRepository = gameRepository;
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
    public Match createMatch(User user, GameType gameType, Double bet, Double multiply, Double result) {
        Game game = gameRepository.findByType(gameType)
                .orElseThrow(()->new EntityNotFoundException("Game not found"));
        Match match = new Match(game, user, bet, multiply, result, LocalDateTime.now());
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
