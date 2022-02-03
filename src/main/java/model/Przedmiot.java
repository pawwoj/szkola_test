package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Przedmiot {

    private static final List<Przedmiot> extension = new ArrayList<>();
    private final List<Ocena> ocenaList = new ArrayList<>();
    private String idPrzedmiotu;
    private String nazwaPrzedmiotu;
    private String nazwiskoNauczyciela;
    private String imieNauczyciela;

    public Przedmiot() {
    }

    public Przedmiot(String idPrzedmiotu, String nazwaPrzedmiotu, String nazwiskoNauczyciela, String imieNauczyciela) {
        this.idPrzedmiotu = idPrzedmiotu;
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.nazwiskoNauczyciela = nazwiskoNauczyciela;
        this.imieNauczyciela = imieNauczyciela;
    }

    public static Przedmiot getPrzedmiotById(String idPrzedmiotu) {
        return extension.stream().filter(klasa1
                -> klasa1.getIdPrzedmiotu().equals(idPrzedmiotu)).findFirst().get();
    }

    public static List<Przedmiot> getExtension() {
        return extension;
    }

    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public String getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public static void readFromFile() {
        try (Scanner fileScanner = new Scanner(new File("przedmioty.txt"))) {
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");
                extension.add(new Przedmiot(
                        splittedArray[0],
                        splittedArray[1],
                        splittedArray[2],
                        splittedArray[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return idPrzedmiotu + " " + nazwaPrzedmiotu + " " + nazwiskoNauczyciela + " " + imieNauczyciela;
    }
}
