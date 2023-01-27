package com.example.rest.controller;

import com.example.rest.dto.CreateMatchFormDto;
import com.example.rest.entity.Match;
import com.example.rest.entity.User;
import com.example.rest.entity.security.GameType;
import com.example.rest.repository.GameRepository;
import com.example.rest.service.MatchService;
import com.example.rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final UserService userService;

    public MatchController(MatchService matchService, UserService userService) {
        this.matchService = matchService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAll() {
        return new ResponseEntity<>(matchService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Long id) {
        Match match = matchService.findById(id);
        if (match == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Match> newMatch(@RequestBody CreateMatchFormDto createMatchFormDto) {
        User user = userService.findById(createMatchFormDto.getUserId());
        user.setTokenAmount(user.getTokenAmount() - createMatchFormDto.getResult());
        userService.save(user);

        try {
            matchService.createMatch(
                    user,
                    GameType.valueOf(createMatchFormDto.getGameType()),
                    createMatchFormDto.getBet(),
                    createMatchFormDto.getMultiply(),
                    createMatchFormDto.getResult());
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
        Match updatedMatch = matchService.save(match);
        if (updatedMatch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

