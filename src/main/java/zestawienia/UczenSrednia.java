package zestawienia;

import java.util.Objects;

public class UczenSrednia {
    private final String nazwisko;
    private final String imie;
    private final String klasa;
    private final double srednia;

    public UczenSrednia(String nazwisko, String imie, String klasa, double srednia) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.klasa = klasa;
        this.srednia = srednia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UczenSrednia that = (UczenSrednia) o;
        return Double.compare(that.srednia, srednia) == 0
                && Objects.equals(nazwisko, that.nazwisko)
                && Objects.equals(imie, that.imie)
                && Objects.equals(klasa, that.klasa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwisko, imie, klasa, srednia);
    }

    @Override
    public String toString() {
        return nazwisko + " " + imie + " " + klasa + " " + srednia;
    }
}
