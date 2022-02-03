import application.Application;

public class Main {
    public static void main(String[] args) {

        Application application = new Application();
        application.run();

    }
}
/*
    Szkoła dysponuje danymi zawartymi w trzech plikach: uczniowie.txt, oceny.txt,
        przedmioty.txt.
        • Plik uczniowie.txt zawiera następujące dane o uczniach: idUcznia, nazwisko, imie,
        ulica, dom, idKlasy.
        • Plik oceny.txt zawiera dane o ocenach: idUcznia, ocena, data, idPrzedmiotu.
        • Plik przedmioty.txt zawiera dane o przedmiotach: idPrzedmiotu, nazwaPrzedmiotu,
        nazwisko_naucz, imie_naucz.


        a) Poza rejonem szkoły leżą ulice Worcella oraz Sportowa. Podaj, ilu uczniów mieszka poza
        rejonem szkoły (czyli na jednej z tych dwóch ulic).

        b) Wypisz wszystkie oceny ucznia Jana Augustyniaka z języka polskiego.

        c) Oblicz, ile dziewcząt i ilu chłopców jest w poszczególnych klasach. Wynik przedstaw
        w postaci zestawienia: idKlasy, liczba dziewcząt, liczba chłopców. Załóż, że imiona
        dziewcząt (i tylko dziewcząt) kończą się na literę a.

        d) Utwórz zestawienie dla klasy 2a zawierające nazwy przedmiotów i średnie ocen klasy
        z tych przedmiotów (średnie podaj z zaokrągleniem do dwóch miejsc po przecinku)
        Zestawienie posortuj nierosnąco według średnich ocen.

        e) Utwórz zestawienie uporządkowane alfabetycznie według nazwisk zawierające wykaz
        osób z klasy 2c, które w kwietniu 2009 roku otrzymały oceny niedostateczne (imię,
        nazwisko, przedmiot).

        f) Podaj nazwisko, imię, klasę oraz średnią ocen osoby, która osiągnęła najwyższą średnią
        ocen w całej szkole (jest tylko jedna taka osoba)
*/
