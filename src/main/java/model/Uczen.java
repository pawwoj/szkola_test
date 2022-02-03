package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uczen {
    private static final List<Uczen> extension = new ArrayList<>();
    private final List<Ocena> ocenaList = new ArrayList<>();
    private String idUcznia;
    private String nazwisko;
    private String imie;
    private String ulica;
    private String dom;
    private Klasa klasa;

    public Uczen() {
    }

    public Uczen(String idUcznia, String nazwisko, String imie, String ulica, String dom, Klasa klasa) {
        this.idUcznia = idUcznia;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.ulica = ulica;
        this.dom = dom;
        this.klasa = klasa;
        klasa.getUczenList().add(this);
    }

    public static Uczen getUczenById(String idUcznia) {
        return extension.stream().filter(klasa1
                -> klasa1.getIdUcznia().equals(idUcznia)).findFirst().get();
    }

    public static List<Uczen> getExtension() {
        return extension;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getUlica() {
        return ulica;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public String getIdUcznia() {
        return idUcznia;
    }

    public static void readFromFile() {
        try (Scanner fileScanner = new Scanner(new File("uczniowie.txt"))) {
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");
                extension.add(new Uczen(
                        splittedArray[0],
                        splittedArray[1],
                        splittedArray[2],
                        splittedArray[3],
                        splittedArray[4],
                        Klasa.getKlasByIdKlasy(splittedArray[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return idUcznia + " " + nazwisko + " " + imie + " " + ulica + " " + dom + " " + klasa.getIdKlasy();
    }
}
