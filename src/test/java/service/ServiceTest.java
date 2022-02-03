package service;

import model.Klasa;
import model.Ocena;
import model.Przedmiot;
import model.Uczen;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {

    @BeforeAll
    public static void setUp() {
        Przedmiot.readFromFile();
        Klasa.readFromFile();
        Uczen.readFromFile();
        Ocena.readFromFile();
    }

    @Test
    void getHowManyStudentsLiveOutsideTheSchoolRegister_shouldSayThatReturnedValueIsNotNull() {
        Service service = new Service();
        assertThat(service.getHowManyStudentsLiveOutsideTheSchoolRegister()).isNotNull();
    }

    @Test
    void getHowManyStudentsLiveOutsideTheSchoolRegister_shouldSayThatReturnIntIsEqualTo15() {
        Service service = new Service();
        assertThat(service.getHowManyStudentsLiveOutsideTheSchoolRegister()).isEqualTo(15);
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getAllGradesStudentFromOneSchoolSubject()).isNotNull();
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListHasSize3() {
        Service service = new Service();
        assertThat(service.getAllGradesStudentFromOneSchoolSubject()).hasSize(3);
    }

    @Test
    void getAllGradesStudentFromOneSchoolSubject_shouldSayThatReturnedListIsEqualToExpectedList() {
        Service service = new Service();
        List<Integer> expected = new ArrayList<>(List.of(3,1,1));
        assertThat(service.getAllGradesStudentFromOneSchoolSubject()).isEqualTo(expected);
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedSetIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getCountedGirlsAndBoysInEachClass()).isNotEmpty();
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedSetHasSize15() {
        Service service = new Service();
        assertThat(service.getCountedGirlsAndBoysInEachClass()).hasSize(15);
    }

    @Test
    void getCountedGirlsAndBoysInEachClass_shouldSayThatReturnedSetContainExpectedList() {
        List<Object> expected = new ArrayList<>(List.of("1a",26,4));
        Service service = new Service();
        assertThat(service.getCountedGirlsAndBoysInEachClass().contains(expected)).isTrue();
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getGradePointAverageForEachSubject()).isNotEmpty();
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListHasSize11() {
        Service service = new Service();
        assertThat(service.getGradePointAverageForEachSubject()).hasSize(11);
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListIsSorted() {
        Service service = new Service();
        assertThat((double) service.getGradePointAverageForEachSubject().get(0).get(1)
                > (double) service.getGradePointAverageForEachSubject().get(1).get(1)).isTrue();
    }

    @Test
    void getGradePointAverageForEachSubject_shouldSayThatReturnedListContainExpectedListAtIndex0() {
        List<Object> expected = new ArrayList<>(List.of("geografia", 3.25));
        Service service = new Service();
        assertThat(service.getGradePointAverageForEachSubject().get(0)).isEqualTo(expected);
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade()).isNotEmpty();
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListHasSize11() {
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade()).hasSize(11);
    }

    @Test
    void getStudentsWithInsufficientGrade_shouldSayThatReturnedListContainExpectedListAtIndex0() {
        List<String> expected = new ArrayList<>(List.of("Jadwiga", "Budzik", "geografia"));
        Service service = new Service();
        assertThat(service.getStudentsWithInsufficientGrade().get(0)).isEqualTo(expected);
    }

    @Test
    void getStudentDataWithTheHighestAverageRating_shouldSayThatReturnedListIsNotEmpty() {
        Service service = new Service();
        assertThat(service.getStudentDataWithTheHighestAverageRating()).isNotEmpty();
    }

    @Test
    void getStudentDataWithTheHighestAverageRating_shouldSayThatReturnedListHasSize4() {
        Service service = new Service();
        assertThat(service.getStudentDataWithTheHighestAverageRating()).hasSize(4);
    }

    @Test
    void getStudentDataWithTheHighestAverageRating_shouldSayThatReturnedListIsEqualToExpectedList() {
        Service service = new Service();
        List<Object> expected = new ArrayList<>(List.of("Barbara", "Ogoszewska", "2d", 3.6));
        assertThat(service.getStudentDataWithTheHighestAverageRating()).isEqualTo(expected);
    }
}