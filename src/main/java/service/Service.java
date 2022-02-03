package service;

import model.Klasa;
import model.Ocena;
import model.Przedmiot;
import model.Uczen;

import java.util.*;
import java.util.regex.Pattern;

public class Service {

    //    a) Poza rejonem szkoły leżą ulice Worcella oraz Sportowa. Podaj, ilu uczniów mieszka poza
//    rejonem szkoły (czyli na jednej z tych dwóch ulic).
    public int getHowManyStudentsLiveOutsideTheSchoolRegister() {
        return Uczen.getExtension()
                .stream().filter(uczen
                        -> uczen.getUlica().equals("Worcella") || uczen.getUlica().equals("Sportowa")).toList().size();
    }

    //    b) Wypisz wszystkie oceny ucznia Jana Augustyniaka z języka polskiego.
    public List<Integer> getAllGradesStudentFromOneSchoolSubject() {
        List<Integer> list = Uczen.getExtension()
                .stream()
                .filter(uczen -> uczen.getImie().equals("Jan")
                        && uczen.getNazwisko().equals("Augustyniak")).findFirst()
                .get().getOcenaList().stream()
                .filter(ocena -> ocena.getPrzedmiot().getNazwaPrzedmiotu().equals("polski"))
                .map(Ocena::getOcena).toList();

        return list;
    }

    //    c) Oblicz, ile dziewcząt i ilu chłopców jest w poszczególnych klasach. Wynik przedstaw
//    w postaci zestawienia: idKlasy, liczba dziewcząt, liczba chłopców. Załóż, że imiona
//    dziewcząt (i tylko dziewcząt) kończą się na literę a.
    public Set<List> getCountedGirlsAndBoysInEachClass() {
        Pattern pattern = Pattern.compile("^[A-z]+a");
        Set<List> zestawienie = new HashSet<>();
        for (Klasa klasa : Klasa.getExtension()) {
            int girls = klasa.getUczenList().stream()
                    .filter(uczen -> pattern.matcher(uczen.getImie()).matches()).toList().size();
            zestawienie.add(new ArrayList<>(List.of(klasa.getIdKlasy(), girls, (klasa.getUczenList().size() - girls))));
        }
        return zestawienie;
    }

    //    d) Utwórz zestawienie dla klasy 2a zawierające nazwy przedmiotów i średnie ocen klasy
//    z tych przedmiotów (średnie podaj z zaokrągleniem do dwóch miejsc po przecinku)
//    Zestawienie posortuj nierosnąco według średnich ocen.
    public List<List<Object>> getGradePointAverageForEachSubject() {
        List<List<Object>> listOfObjectList = new ArrayList<>();
        List<Uczen> uczenList = Klasa.getExtension().stream().filter(klasa -> klasa.getIdKlasy().equals("2a"))
                .findFirst().get().getUczenList();

        for (Przedmiot przedmiot : Przedmiot.getExtension()) {
            List<Integer> integerList = new ArrayList<>();
            uczenList.stream().forEach(uczen -> uczen.getOcenaList().stream()
                    .filter(ocena -> ocena.getPrzedmiot().getNazwaPrzedmiotu()
                            .equals(przedmiot.getNazwaPrzedmiotu()))
                    .forEach(ocena -> integerList.add(ocena.getOcena())));

            double result = Double.valueOf(integerList.stream()
                    .reduce(0, Integer::sum)) / (double) integerList.size();
            result = result * 100;
            result = Math.round(result);
            result = result / 100;

            listOfObjectList.add(new ArrayList<>(List.of(przedmiot.getNazwaPrzedmiotu(), result)));
        }
        listOfObjectList.sort(Comparator.comparingDouble(value -> (double) value.get(1)));
        Collections.reverse(listOfObjectList);
        return listOfObjectList;
    }

    //    e) Utwórz zestawienie uporządkowane alfabetycznie według nazwisk zawierające wykaz
//    osób z klasy 2c, które w kwietniu 2009 roku otrzymały oceny niedostateczne (imię, nazwisko, przedmiot).
    public List<List<String>> getStudentsWithInsufficientGrade() {
        List<List<String>> listOfList = new ArrayList<>();
        List<Ocena> ocenaTempList = new ArrayList<>();
        int grade = 1;
        Pattern pattern = Pattern.compile("2009-05-\\d{2}");

        List<Uczen> uczenList = Klasa.getExtension().stream().filter(klasa -> klasa.getIdKlasy().equals("2c"))
                .findFirst().get().getUczenList();

        for (Uczen uczen : uczenList) {
            ocenaTempList.add(uczen.getOcenaList().stream()
                    .filter(ocena -> pattern.matcher(ocena.getData().toString()).matches()
                            && ocena.getOcena() == grade).findFirst().orElse(null));
        }
        while (ocenaTempList.remove(null)) ;

        //  ocenaTempList.stream().sorted(Comparator.comparing(ocena -> ocena.getUczen().getNazwisko())).toList();

        ocenaTempList.forEach(ocena
                -> listOfList.add(new ArrayList<>(List.of(
                ocena.getUczen().getImie(),
                ocena.getUczen().getNazwisko(),
                ocena.getPrzedmiot().getNazwaPrzedmiotu()))));
        // listOfList.stream().distinct();
        return listOfList;
    }

    //    f) Podaj nazwisko, imię, klasę oraz średnią ocen osoby, która osiągnęła najwyższą średnią
//    ocen w całej szkole (jest tylko jedna taka osoba)
    public List<Object> getStudentDataWithTheHighestAverageRating() {
        double maxAverage = 0.00;
        Uczen bestStudent = null;
        for (Uczen uczen : Uczen.getExtension()) {
            double result =
                    (double) uczen.getOcenaList().stream().map(Ocena::getOcena)
                            .reduce(0, Integer::sum) / (double) uczen.getOcenaList().size();
            if (result > maxAverage) {
                maxAverage = result;
                bestStudent = uczen;
            }
        }
        maxAverage = maxAverage * 100;
        maxAverage = Math.round(maxAverage);
        maxAverage = maxAverage / 100;
        return new ArrayList<>(List.of(bestStudent.getImie(), bestStudent.getNazwisko(),
                bestStudent.getKlasa().getIdKlasy(), maxAverage));
    }
}