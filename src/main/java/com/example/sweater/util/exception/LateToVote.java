package com.example.sweater.util.exception;

public class LateToVote extends RuntimeException {
    public LateToVote(String message) {
        super(message);
    }
}