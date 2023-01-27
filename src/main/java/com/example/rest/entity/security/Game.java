package com.example.rest.entity.security;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    @SequenceGenerator(name = "game_id_generator", sequenceName = "sq_game_id", allocationSize = 1)
    private long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private GameType type;

    public Game() {
    }

    public Game(GameType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }
}
