package com.example.rest.dto;

import com.example.rest.entity.User;
import com.example.rest.entity.security.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


public class CreateMatchFormDto  {

    private long userId;
    private String gameType;
    private double bet;
    private double multiply;
    private double result;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
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
}