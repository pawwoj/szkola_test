package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Klasa {
    private static final List<Klasa> extension = new ArrayList<>();
    private final List<Uczen> uczenList = new ArrayList<>();
    private final String idKlasy;

    public Klasa(String idKlasy) {
        this.idKlasy = idKlasy;
    }

    public static Klasa getKlasByIdKlasy(String idKlasy) {
        return extension.stream().filter(klasa1
                -> klasa1.getIdKlasy().equals(idKlasy)).findFirst().get();
    }

    public static List<Klasa> getExtension() {
        return extension;
    }

    public static void readFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");
                if (!extension.contains(new Klasa(splittedArray[5])))
                    extension.add(new Klasa(splittedArray[5]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Uczen> getUczenList() {
        return uczenList;
    }

    public String getIdKlasy() {
        return idKlasy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klasa klasa = (Klasa) o;
        return idKlasy.equals(klasa.idKlasy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlasy);
    }

    @Override
    public String toString() {
        return idKlasy;
    }
}
