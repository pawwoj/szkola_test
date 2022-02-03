package application;

import model.Klasa;
import model.Ocena;
import model.Przedmiot;
import model.Uczen;
import service.Service;

public class Application {

    public void run() {
        Przedmiot.readFromFile();
        Klasa.readFromFile();
        Uczen.readFromFile();
        Ocena.readFromFile();
        Service service = new Service();


        /*DEMO*/
        System.out.println("a) Poza rejonem szkoły leżą ulice Worcella oraz Sportowa.\n" +
                " Podaj, ilu uczniów mieszka poza rejonem szkoły (czyli na jednej z tych dwóch ulic).");
        System.out.println(service.getHowManyStudentsLiveOutsideTheSchoolRegister());
        System.out.println();

        System.out.println("b) Wypisz wszystkie oceny ucznia Jana Augustyniaka z języka polskiego.");
        System.out.println(service.getAllGradesStudentFromOneSchoolSubject());
        System.out.println();

        System.out.println("c) Oblicz, ile dziewcząt i ilu chłopców jest w poszczególnych klasach. Wynik przedstaw\n" +
                "w postaci zestawienia: idKlasy, liczba dziewcząt, liczba chłopców. Załóż, że imiona\n" +
                "dziewcząt (i tylko dziewcząt) kończą się na literę a.");
        System.out.println(service.getCountedGirlsAndBoysInEachClass());
        System.out.println();

        System.out.println("d) Utwórz zestawienie dla klasy 2a zawierające nazwy przedmiotów i średnie ocen klasy\n" +
                "z tych przedmiotów (średnie podaj z zaokrągleniem do dwóch miejsc po przecinku)\n" +
                "Zestawienie posortuj nierosnąco według średnich ocen.");
        System.out.println(service.getGradePointAverageForEachSubject());
        System.out.println();

        System.out.println("e) Utwórz zestawienie uporządkowane alfabetycznie według nazwisk zawierające wykaz\n" +
                "osób z klasy 2c, które w kwietniu 2009 roku otrzymały oceny niedostateczne \n" +
                "(imię, nazwisko, przedmiot).");
        System.out.println(service.getStudentsWithInsufficientGrade());
        System.out.println();

        System.out.println("f) Podaj nazwisko, imię, klasę oraz średnią ocen osoby, \n" +
                "która osiągnęła najwyższą średnią\n" +
                "ocen w całej szkole (jest tylko jedna taka osoba)");
        System.out.println(service.getStudentDataWithTheHighestAverageRating());
    }
}
