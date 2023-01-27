package com.example.rest.controller;

import com.example.rest.entity.Match;
import com.example.rest.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
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
    public ResponseEntity<Match> newMatch(@RequestBody Match match) {
        Match createdMatch = matchService.save(match);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
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

