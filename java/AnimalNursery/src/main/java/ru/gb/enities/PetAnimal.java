package ru.gb.enities;

public class PetAnimal extends Animal{
    public PetAnimal(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Pet Animal";
    }
}
