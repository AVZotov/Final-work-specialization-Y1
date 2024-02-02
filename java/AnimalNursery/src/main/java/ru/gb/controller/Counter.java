package ru.gb.controller;

public class Counter implements AutoCloseable{
    static int sum;
    static
    {
        sum = 0;
    }


    @Override
    public void close() {
        System.out.println("Counter is closed in stream");
    }

    public void add() {
        sum ++;
    }
}
