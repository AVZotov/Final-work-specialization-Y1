package ru.gb.enities;

public class PackAnimal extends Animal{
    public PackAnimal(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Pack Animal";
    }
}
