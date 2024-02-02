package ru.gb.enities;

public class PackAnimal extends Animal{
    public PackAnimal(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Pack Animal";
    }

    @Override
    public void print() {
       System.out.printf("type: %s, name: %s", this.getType(), this.getName());
    }

    public String getKind(){
        String[] animalKind = this.getClass().descriptorString().split("/");
        return animalKind[animalKind.length - 1];
    }
}
