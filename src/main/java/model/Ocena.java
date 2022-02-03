package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Ocena {

    private final int ocena;
    private final LocalDate data;
    private final Uczen uczen;
    private final Przedmiot przedmiot;

    public Ocena(Uczen uczen, int ocena, LocalDate data, Przedmiot przedmiot) {
        this.ocena = ocena;
        this.data = data;
        this.uczen = uczen;
        this.przedmiot = przedmiot;
        uczen.getOcenaList().add(this);
        przedmiot.getOcenaList().add(this);
    }

    public int getOcena() {
        return ocena;
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

    public static void readFromFile() {
        try (Scanner fileScanner = new Scanner(new File("oceny.txt"))) {
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

    @Override
    public String toString() {
        return uczen.getIdUcznia() + " " + ocena + " " + data + " " + przedmiot.getNazwaPrzedmiotu();
    }
}
