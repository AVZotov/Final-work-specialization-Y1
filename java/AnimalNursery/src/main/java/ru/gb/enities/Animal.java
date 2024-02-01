package ru.gb.enities;

public abstract class Animal {
    private final String name;

    protected Animal(String name) {
        this.name = name;
    }

    public String GetName(){
        return name;
    }
}



