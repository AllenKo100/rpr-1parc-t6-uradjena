package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NastavnikTest {
    @Test
    public void klasePostoje() {
        Nastavnik nastavnik = new Ucitelj("Suljo Suljić");
        Nastavnik nastavnik2 = new NastavnikOsnovneSkole("Edo Edić");
        Nastavnik nastavnik3 = new Profesor("Nerma Nermić");
        Osoba o = new NastavnikOsnovneSkole("Alma Almić");
    }

    @Test
    public void sopl() {
        Nastavnik nastavnik = new Ucitelj("Suljo Suljić");
        Nastavnik nastavnik2 = new NastavnikOsnovneSkole("Edo Edić");
        Nastavnik nastavnik3 = new Profesor("Nerma Nermić");
        // Ovo će dati isti efekat kao sout
        String rezultat = "" + nastavnik;
        assertEquals("Učitelj Suljo Suljić", rezultat);

        rezultat = "" + nastavnik2;
        assertEquals("Nastavnik osnovne škole Edo Edić", rezultat);
        rezultat = "" + nastavnik3;
        assertEquals("Profesor Nerma Nermić", rezultat);
    }


    @Test
    public void testRodjendan() {
        Ucitelj d = new Ucitelj("Edo Edić");
        LocalDate datum = LocalDate.of(1985, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        String cestitka = d.rodjendan(datum);
        assertEquals("Sretan rodjendan!", cestitka);

        Ucitelj d2 = new Ucitelj("Ana Anić");
        LocalDate datum2 = LocalDate.of(1995, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+1);
        cestitka = d2.rodjendan(datum2);
        assertEquals("", cestitka);
    }
}