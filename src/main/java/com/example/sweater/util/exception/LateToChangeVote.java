package com.example.sweater.util.exception;

public class LateToChangeVote extends RuntimeException {
    public LateToChangeVote(String message) {
        super(message);
    }
}