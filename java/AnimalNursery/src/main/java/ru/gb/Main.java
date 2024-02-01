package ru.gb;

import ru.gb.enities.Animal;
import ru.gb.enities.Dog;
import ru.gb.services.FileService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Bobik");
        dog1.addCommand("Go");

        Dog dog2 = new Dog("Sharik");
        dog2.addCommand("Seat");

        List<Animal> animals = new ArrayList<>();
        animals.add(dog1);
        animals.add(dog2);

        FileService.Save(animals);

        List<Animal> animalList = FileService.Load();

        for (Animal animal : animalList){
            System.out.println(animal.GetName());
            animal.printCommands();
            System.out.println(animal.getType());
        }
    }
}