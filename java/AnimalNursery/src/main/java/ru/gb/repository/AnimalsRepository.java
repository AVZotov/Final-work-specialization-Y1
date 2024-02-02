package ru.gb.repository;

import ru.gb.enities.Animal;
import ru.gb.services.FileService;

import java.util.ArrayList;
import java.util.List;

public class AnimalsRepository {
    private List<Animal> animals = new ArrayList<>();

    public AnimalsRepository() {
        this.animals = FileService.Load();
    }
    public List<Animal> getAnimals(){ return animals; }

    public void addAnimal(Animal animal){ animals.add(animal); }
}
