package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Ocena {

    private static final List<Ocena> extension = new ArrayList<>();
    private final int stopien;
    private final LocalDate data;
    private final Uczen uczen;
    private final Przedmiot przedmiot;

    public Ocena(Uczen uczen, int stopien, LocalDate data, Przedmiot przedmiot) {
        this.stopien = stopien;
        this.data = data;
        this.uczen = uczen;
        this.przedmiot = przedmiot;
        uczen.getOcenaList().add(this);
        przedmiot.getOcenaList().add(this);
        extension.add(this);
    }

    public static List<Ocena> getExtension() {
        return extension;
    }

    public static void readFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");
                new Ocena(
                        Uczen.getUczenById(splittedArray[0]),
                        Integer.parseInt(splittedArray[1]),
                        LocalDate.parse(splittedArray[2]),
                        Przedmiot.getPrzedmiotById(splittedArray[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getStopien() {
        return stopien;
    }

    public LocalDate getData() {
        return data;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ocena ocena = (Ocena) o;
        return stopien == ocena.stopien
                && Objects.equals(data, ocena.data)
                && Objects.equals(uczen, ocena.uczen)
                && Objects.equals(przedmiot, ocena.przedmiot);
    }

    @Override
    public String toString() {
        return uczen.getIdUcznia() + " " + stopien + " " + data + " " + przedmiot.getNazwaPrzedmiotu();
    }
}
