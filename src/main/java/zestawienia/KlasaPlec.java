package zestawienia;

import java.util.Objects;

public class KlasaPlec {
    private final String nazwaKlasy;
    private final int dziewczyny;
    private final int chlopcy;

    public KlasaPlec(String nazwaKlasy, int dziewczyny, int chlopcy) {
        this.nazwaKlasy = nazwaKlasy;
        this.dziewczyny = dziewczyny;
        this.chlopcy = chlopcy;
    }

    @Override
    public String toString() {
        return "[" + nazwaKlasy + ", " + dziewczyny + ", " + chlopcy + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlasaPlec klasaPlec = (KlasaPlec) o;
        return dziewczyny == klasaPlec.dziewczyny
                && chlopcy == klasaPlec.chlopcy
                && Objects.equals(nazwaKlasy, klasaPlec.nazwaKlasy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwaKlasy, dziewczyny, chlopcy);
    }
}
