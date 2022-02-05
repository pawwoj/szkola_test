package zestawienia;

public class PrzedmiotSrednia {
    String nazwaPrzedmiotu;
    double srednia;

    public PrzedmiotSrednia(String nazwaPrzedmiotu, double srednia) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.srednia = srednia;
    }

    public double getSrednia() {
        return srednia;
    }

    @Override
    public String toString() {
        return nazwaPrzedmiotu + " " + srednia;
    }
}
