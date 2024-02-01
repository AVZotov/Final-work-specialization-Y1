package ru.gb.enities;

import ru.gb.enities.commands.AnimalCommand;

import java.util.ArrayList;
import java.util.List;

public class Cat extends PetAnimal {
    private String animalKind;
    private final List<AnimalCommand> commands = new ArrayList<>();
    protected Cat(String name) {
        super(name);
    }
    public void setAnimalKind(String animalKind){
        this.animalKind = animalKind;
    }
    public String getAnimalKind(){
        return this.animalKind;
    }
    public void addCommand(String commandDescription){
        AnimalCommand newCommand = new AnimalCommand();
        newCommand.setCommand(commandDescription);
        commands.add(newCommand);
    }
    public List<AnimalCommand> getCommands(){
        return commands;
    }
}

