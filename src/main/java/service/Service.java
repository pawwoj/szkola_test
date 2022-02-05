package service;

import model.*;
import zestawienia.KlasaPlec;
import zestawienia.PrzedmiotSrednia;
import zestawienia.UczenPrzedmiot;
import zestawienia.UczenSrednia;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Service {

    //    a) Poza rejonem szkoły leżą ulice Worcella oraz Sportowa. Podaj, ilu uczniów mieszka poza
//    rejonem szkoły (czyli na jednej z tych dwóch ulic).
    public int getHowManyStudentsLiveOutsideTheSchoolRegister(List<String> uliceList) {
        return Uczen.getExtension()
                .stream().filter(uczen
                        -> uliceList.contains(uczen.getUlica())).toList().size();
    }

    //    b) Wypisz wszystkie oceny ucznia Jana Augustyniaka z języka polskiego.
    public List<Integer> getAllGradesStudentFromOneSchoolSubject(String imie, String nazwisko, String przedmiot) {
        List<Integer> list = Uczen.getExtension()
                .stream()
                .filter(uczen -> uczen.getImie().equals(imie)
                        && uczen.getNazwisko().equals(nazwisko)).findFirst()
                .get().getOcenaList().stream()
                .filter(ocena -> ocena.getPrzedmiot().getNazwaPrzedmiotu().equals(przedmiot))
                .map(Ocena::getStopien).toList();

        return list;
    }

    //    c) Oblicz, ile dziewcząt i ilu chłopców jest w poszczególnych klasach. Wynik przedstaw
//    w postaci zestawienia: idKlasy, liczba dziewcząt, liczba chłopców. Załóż, że imiona
//    dziewcząt (i tylko dziewcząt) kończą się na literę a.
    public List<KlasaPlec> getCountedGirlsAndBoysInEachClass() {
        Pattern pattern = Pattern.compile("^[A-z]+a");
        List<KlasaPlec> zestawienie = new ArrayList<>();
        for (Klasa klasa : Klasa.getExtension()) {
            int girls = klasa.getUczenList().stream()
                    .filter(uczen -> pattern.matcher(uczen.getImie()).matches()).toList().size();
            zestawienie.add(new KlasaPlec(klasa.getIdKlasy(), girls, (klasa.getUczenList().size() - girls)));
        }
        return zestawienie;
    }

    //    d) Utwórz zestawienie dla klasy 2a zawierające nazwy przedmiotów i średnie ocen klasy
//    z tych przedmiotów (średnie podaj z zaokrągleniem do dwóch miejsc po przecinku)
//    Zestawienie posortuj nierosnąco według średnich ocen.
    public List<PrzedmiotSrednia> getGradePointAverageForEachSubject(String podanaKlasa) {
        List<PrzedmiotSrednia> przedmiotSredniaList = new ArrayList<>();

        for (Przedmiot przedmiot : Przedmiot.getExtension()) {
            double srednia = Ocena.getExtension().stream()
                    .filter(ocena -> ocena.getUczen().getKlasa().getIdKlasy().equals(podanaKlasa)
                            && ocena.getPrzedmiot().getNazwaPrzedmiotu().equals(przedmiot.getNazwaPrzedmiotu()))
                    .collect(Collectors.averagingDouble(Ocena::getStopien));
            przedmiotSredniaList.add(new PrzedmiotSrednia(przedmiot.getNazwaPrzedmiotu(), doDwochMiejsc(srednia)));
        }

        przedmiotSredniaList.sort(Collections
                .reverseOrder(Comparator.comparingDouble(PrzedmiotSrednia::getSrednia)));

        return przedmiotSredniaList;
    }

    //    e) Utwórz zestawienie uporządkowane alfabetycznie według nazwisk zawierające wykaz
//    osób z klasy 2c, które w kwietniu 2009 roku otrzymały oceny niedostateczne (imię, nazwisko, przedmiot).
    public List<UczenPrzedmiot> getStudentsWithInsufficientGrade(String podanaKlasa, int podanyStopien, String data) {
        List<UczenPrzedmiot> uczenPrzedmiots = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        if (data.length() == 4) {
            pattern = Pattern.compile(data + "-\\d{2}-\\d{2}");
        }
        if (data.length() == 7) {
            pattern = Pattern.compile(data + "-\\d{2}");
        }

        Pattern finalPattern = pattern;
        Ocena.getExtension().stream().filter(ocena -> ocena.getStopien() == podanyStopien
                        && ocena.getUczen().getKlasa().getIdKlasy().equals(podanaKlasa)
                        && finalPattern.matcher(ocena.getData().toString()).matches())
                .iterator().forEachRemaining(ocena -> uczenPrzedmiots.add(new UczenPrzedmiot(
                        ocena.getUczen().getImie(),
                        ocena.getUczen().getNazwisko(),
                        ocena.getPrzedmiot().getNazwaPrzedmiotu())));

        return uczenPrzedmiots.stream().sorted(Comparator.comparing(UczenPrzedmiot::getNazwisko)).toList();
    }

    //    f) Podaj nazwisko, imię, klasę oraz średnią ocen osoby, która osiągnęła najwyższą średnią
//    ocen w całej szkole (jest tylko jedna taka osoba)
    public UczenSrednia getStudentDataWithTheHighestAverageRating() {
        double maxAverage = 0.00;
        Uczen bestStudent = null;
        for (Uczen uczen : Uczen.getExtension()) {
            double result = uczen.getOcenaList().stream()
                    .collect(Collectors.averagingDouble(Ocena::getStopien));
            if (result > maxAverage) {
                maxAverage = result;
                bestStudent = uczen;
            }
        }
        return new UczenSrednia(bestStudent.getImie(), bestStudent.getNazwisko(),
                bestStudent.getKlasa().getIdKlasy(), doDwochMiejsc(maxAverage));
    }

    private double doDwochMiejsc(double d) {
        d = d * 100;
        d = Math.round(d);
        d = d / 100;
        return d;
    }
}