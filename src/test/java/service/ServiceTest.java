package service;

import model.Klasa;
import model.Ocena;
import model.Przedmiot;
import model.Uczen;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import zestawienia.KlasaPlec;
import zestawienia.PrzedmiotSrednia;
import zestawienia.UczenPrzedmiot;
import zestawienia.UczenSrednia;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {

    @BeforeAll
    public static void setUp() {
        Przedmiot.readFromFile("przedmiotyTest.txt");
        Klasa.readFromFile("uczniowieTest.txt");
        Uczen.readFromFile("uczniowieTest.txt");
        Ocena.readFromFile("ocenyTest.txt");
    }

    @Test
    void getHowManyStudentsLiveOutsideTheSchoolRegister_shouldSayThatReturnedValueIsNotNull() {
        Service service = new Service();
        List<String> ulicePozaRejonemList = new ArrayList<>(List.of("Daleka", "Bardzodaleka"));
        assertThat(service.getHowManyStudentsLiveOutsideTheSchoolRegister(ulicePozaRejonemList)).isNotNull();
    }

    @Test
    void getHowManyStudentsLiveOutsideTheSchoolRegister_shouldSayThatReturnIntIsEqualTo4() {
        Service service = new Service();
        List<String> ulicePozaRejonemList = new ArrayList<>(List.of("Daleka", "Bardzodaleka"));
        assertThat(service.getHowManyStudentsLiveOutsideTheSchoolRegister(ulicePozaRejonemList)).isEqualTo(4);
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListIsNotNull() {
        Service service = new Service();
        String imie = "Edyta";
        String nazwisko = "Szukana";
        String przedmiot = "angielski";
        assertThat(service.getAllGradesStudentFromOneSchoolSubject(imie, nazwisko, przedmiot)).isNotNull();
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListHasSize4() {
        Service service = new Service();
        String imie = "Edyta";
        String nazwisko = "Szukana";
        String przedmiot = "angielski";
        assertThat(service.getAllGradesStudentFromOneSchoolSubject(imie, nazwisko, przedmiot)).hasSize(4);
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListIsEqualToExpectedList() {
        Service service = new Service();
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4));
        String imie = "Edyta";
        String nazwisko = "Szukana";
        String przedmiot = "angielski";
        assertThat(service.getAllGradesStudentFromOneSchoolSubject(imie, nazwisko, przedmiot)).isEqualTo(expected);
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getCountedGirlsAndBoysInEachClass()).isNotEmpty();
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedListHasSize3() {
        Service service = new Service();
        int expected = 3;
        assertThat(service.getCountedGirlsAndBoysInEachClass()).hasSize(expected);
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedListIsEqualAsExpectedList() {
        List<KlasaPlec> expected = new ArrayList<>(List.of(
                new KlasaPlec("1a", 2, 1),
                new KlasaPlec("3c", 3, 0),
                new KlasaPlec("2b", 0, 3)));
        Service service = new Service();
        assertThat(service.getCountedGirlsAndBoysInEachClass().equals(expected)).isTrue();
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getGradePointAverageForEachSubject("3c")).isNotEmpty();
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListHasSize3() {
        Service service = new Service();
        assertThat(service.getGradePointAverageForEachSubject("3c")).hasSize(3);
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListIsSorted() {
        Service service = new Service();
        List<PrzedmiotSrednia> psList = service.getGradePointAverageForEachSubject("3c");
        assertThat(psList.get(0).getSrednia() > psList.get(1).getSrednia()
                && psList.get(1).getSrednia() > psList.get(2).getSrednia()).isTrue();
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade("1a", 1, "2009-05"))
                .isNotEmpty();
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListHasSize2() {
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade("1a", 1, "2009-05"))
                .hasSize(2);
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListisEqualToExpectedList() {
        List<UczenPrzedmiot> expected = new ArrayList<>(List.of(
                new UczenPrzedmiot("Marian", "Berdysow", "niemiecki"),
                new UczenPrzedmiot("Danuta", "Zaczynska", "angielski")));
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade("1a", 1, "2009-05")
                .equals(expected));
    }

    @Test
    void getStudentDataWithTheHighestAverageRating_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getStudentDataWithTheHighestAverageRating()).isNotNull();
    }

    @Test
    void getStudentDataWithTheHighestAverageRating_shouldSayThatReturnedObjectIsEqualToExpected() {
        Service service = new Service();
        UczenSrednia expected = new UczenSrednia("Ten", "Najlepszy", "2b", 4.57);
        assertThat(service.getStudentDataWithTheHighestAverageRating()).isEqualTo(expected);
    }
}