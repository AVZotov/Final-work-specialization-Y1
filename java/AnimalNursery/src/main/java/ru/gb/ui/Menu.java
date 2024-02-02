package ru.gb.ui;

import ru.gb.controller.Controller;
import ru.gb.controller.Counter;
import ru.gb.repository.AnimalsRepository;
import ru.gb.services.FileService;

import java.util.Scanner;

public class Menu {
    Controller controller;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        try (Scanner in = new Scanner(System.in); Counter counter = new Counter()) {
            boolean isMenuOpened = true;

            while (isMenuOpened) {
                printMainMenu();
                String userSelection = in.next();

                switch (userSelection) {
                    case "1":
                        controller.printAllAnimals();
                        break;
                    case "2":
                        if (controller.addAnimal(getAnimalDetails(in))) {counter.add(); }
                        break;
                    case "3":
                        controller.getAnimal(getIndex(in)).printCommands();
                        break;
                    case "4":
                        controller.addCommand(getCommandName(in), getIndex(in));
                        break;
                    case "5":
                        FileService.Save(controller.getAnimals());
                        break;
                    case "6":
                        isMenuOpened = false;
                        break;
                    default:
                        System.out.println("Error! Please try again");
                        break;
                }
            }
        }
    }

    private String[] getAnimalDetails(Scanner in) {
        String[] animalDetails = new String[2];
        printAnimalKind();
        animalDetails[0] = in.next();
        System.out.println("Enter animal name");
        animalDetails[1] = in.next();
        return animalDetails;
    }

    private void printMainMenu(){
        System.out.println(
                """
                        1: Show all animals
                        2: Add new animal
                        3: Show commands
                        4: Add new command
                        5: Save changes
                        6: Exit""");
    }

    private void printAnimalKind(){
        System.out.println(
                """
                        1: Dog
                        2: Cat
                        3: Hamster
                        4: Horse
                        5: Camel
                        6: Donkey""");
    }

    private String getIndex(Scanner in){
        System.out.println("Select animal");
        controller.printAllAnimals();
        return in.next();
    }

    private String getCommandName(Scanner in){
        System.out.println("Enter command");
        return in.next();
    }
}
