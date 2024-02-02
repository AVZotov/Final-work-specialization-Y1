package ru.gb.controller;

import ru.gb.enities.*;
import ru.gb.exceptions.IncorrectCommandException;
import ru.gb.exceptions.IncorrectTypeException;
import ru.gb.exceptions.incorrectNameException;
import ru.gb.repository.AnimalsRepository;

import java.util.List;

public class Controller{
    AnimalsRepository repository;

    public Controller(AnimalsRepository repository) {
        this.repository = repository;
    }

    public boolean addAnimal(String[] animalDetails){
        if (animalDetails[1].isEmpty()){
            throw new incorrectNameException("No name entered");
        }
        return switch (animalDetails[0]) {
            case "1" -> {
                repository.addAnimal(new Dog(animalDetails[1]));
                yield true;
            }
            case "2" -> {
                repository.addAnimal(new Cat(animalDetails[1]));
                yield true;
            }
            case "3" -> {
                repository.addAnimal(new Hamster(animalDetails[1]));
                yield true;
            }
            case "4" -> {
                repository.addAnimal(new Horse(animalDetails[1]));
                yield true;
            }
            case "5" -> {
                repository.addAnimal(new Camel(animalDetails[1]));
                yield true;
            }
            case "6" -> {
                repository.addAnimal(new Donkey(animalDetails[1]));
                yield true;
            }
            default -> throw new IncorrectTypeException("wrong type selected");
        };
    }

    public List<Animal> getAnimals(){
        return repository.getAnimals();
    }

    public Animal getAnimal(String userInput){
        int index = 0;
        try {
            index = Integer.parseInt(userInput);
        } catch (NumberFormatException e){
            System.out.println(e);
        }

        try {
            return repository.getAnimals().get(index - 1);
        } catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        return null;
    }

    public void printAllAnimals(){
        int counter = 1;
        List<Animal> animals = repository.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals in collection");
            return;
        }

        for (Animal animal : animals){
            System.out.printf("%d. %s %s name: %s\n",counter, animal.getType(), animal.getKind(), animal.getName());
            counter ++;
        }
    }

    public void addCommand(String command, String userInput){
        if (command.isEmpty()){
            throw new IncorrectCommandException("Command field is empty");
        }

        int index = 0;

        try {
            index = Integer.parseInt(userInput);
        } catch (NumberFormatException e){
            System.out.println(e);
        }

        try {
            Animal animal = repository.getAnimals().get(index - 1);
            animal.addCommand(command);
        } catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
    }
}
