package ru.gb.enities.commands;

import java.io.Serializable;

public class AnimalCommand implements Serializable {
    private String command;

    public void setCommand(String command){
        this.command = command;
    }
    public String getCommand(){
        return this.command;
    }
}
