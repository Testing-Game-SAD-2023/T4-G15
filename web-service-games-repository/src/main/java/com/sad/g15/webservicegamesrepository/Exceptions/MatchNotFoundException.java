package com.sad.g15.webservicegamesrepository.Exceptions;

public class MatchNotFoundException extends Exception{
    public MatchNotFoundException(String message) {
        super(message);
    }

    public MatchNotFoundException() {
        super("Match not found");
    }
}
