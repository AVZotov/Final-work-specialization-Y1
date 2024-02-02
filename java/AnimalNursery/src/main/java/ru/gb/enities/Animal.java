package ru.gb.enities;

import ru.gb.enities.commands.AnimalCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal implements Serializable {
    private final String name;
    private final List<AnimalCommand> commands = new ArrayList<>();

    public Animal(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addCommand(String commandDescription){
        AnimalCommand newCommand = new AnimalCommand();
        newCommand.setCommand(commandDescription);
        commands.add(newCommand);
    }
    public List<AnimalCommand> getCommands(){
        return commands;
    }

    public void printCommands(){
        for (AnimalCommand command : commands){
            System.out.println(command.getCommand());
        }
    }
    public abstract String getType();

    public abstract void print();

    public abstract String getKind();
}



