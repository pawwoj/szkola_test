package zestawienia;

public class UczenPrzedmiot {

    private final String imie;
    private final String nazwisko;
    private final String przedmiot;

    public UczenPrzedmiot(String imie, String nazwisko, String przedmiot) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.przedmiot = przedmiot;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " " + przedmiot;
    }
}
