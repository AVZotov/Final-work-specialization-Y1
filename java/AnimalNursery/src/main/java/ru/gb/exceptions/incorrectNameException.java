package ru.gb.exceptions;

public class incorrectNameException extends RuntimeException{
    public incorrectNameException(String message) {
        super(message);
    }
}
