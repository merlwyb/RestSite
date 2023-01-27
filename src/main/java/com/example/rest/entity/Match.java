package com.example.rest.entity;

import com.example.rest.entity.security.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_generator")
    @SequenceGenerator(name = "match_id_generator", sequenceName = "sq_match_id", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MATCH_GAME",
            joinColumns = @JoinColumn(name = "MATCH_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_ID")
    )
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MATCH_USER",
            joinColumns = @JoinColumn(name = "MATCH_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private User user;
    private double bet;
    private double multiply;
    private double result;
    private LocalDateTime matchDateTime;

    public Match() {
    }

    public Match(Game game, User user, double bet, double multiply, double result, LocalDateTime matchDateTime) {
        this.game = game;
        this.user = user;
        this.bet = bet;
        this.multiply = multiply;
        this.result = result;
        this.matchDateTime = matchDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getMultiply() {
        return multiply;
    }

    public void setMultiply(double multiply) {
        this.multiply = multiply;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }
}