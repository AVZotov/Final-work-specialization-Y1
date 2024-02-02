package ru.gb;

import ru.gb.controller.Controller;
import ru.gb.repository.AnimalsRepository;
import ru.gb.ui.Menu;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu(new Controller(new AnimalsRepository()));
        menu.start();
    }
}