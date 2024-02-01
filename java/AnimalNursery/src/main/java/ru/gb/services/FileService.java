package ru.gb.services;

import ru.gb.enities.Animal;

import java.io.*;
import java.util.List;

public class FileService {
    private static final String PATH = "animals.bin";
    public static void Save(List<Animal> animals) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))){
            oos.writeObject(animals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Animal> Load(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))){
            return (List<Animal>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
