package ru.gb.exceptions;

public class IncorrectCommandException extends RuntimeException{
    public IncorrectCommandException(String message) {
        super(message);
    }
}
