package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UcenikTest {
    @Test
    public void klasePostoje() {
        Ucenik s = new Osnovac("Pero Peric");
        Ucenik s2 = new Srednjoskolac("Hana Hanic");
        Osoba o = new Srednjoskolac("Alma Almic");
    }

    @Test
    public void sout() {
        Ucenik s = new Osnovac("Pero Perić");
        s.setBrojKnjizice("54321");
        // Ovo će dati isti efekat kao sout
        String rezultat = "" + s;
        assertEquals("Učenik osnovne škole Pero Perić (54321), prosjek ocjena: 0.0", rezultat);

        Ucenik s2 = new Srednjoskolac("Hana Hanić");
        s2.setBrojKnjizice("23232");
        rezultat = "" + s2;
        assertEquals("Učenik srednje škole Hana Hanić (23232), prosjek ocjena: 0.0", rezultat);
    }

    @Test
    public void prosjekOcjena() {
        Ucenik s = new Osnovac("Sara Sarač");
        s.setBrojKnjizice("12121");
        try {
            s.dodajOcjenu(4);
            s.dodajOcjenu(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(4, s.prosjek());

        try {
            s.dodajOcjenu(5);
            s.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(4.5, s.prosjek());
    }


    @Test
    public void prosjekOcjenaString() {
        Ucenik s = new Osnovac("Sara Sarač");
        s.setBrojKnjizice("12121");
        try {
            s.dodajOcjenu(4);
            s.dodajOcjenu(4);
            s.dodajOcjenu(3);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        // Provjeravamo da li je prosjek u stringu tačan
        String rezultat = "" + s;
        assertEquals("Učenik osnovne škole Sara Sarač (12121), prosjek ocjena: 3.7", rezultat);

        try {
            s.dodajOcjenu(3);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        rezultat = "" + s;
        assertEquals("Učenik osnovne škole Sara Sarač (12121), prosjek ocjena: 3.5", rezultat);
    }

    @Test
    public void prosjekOcjenaSrednja() {
        Srednjoskolac s = new Srednjoskolac("Alma Almić");
        s.setBrojKnjizice("33221");
        try {
            s.dodajOcjenu(4);
            s.dodajOcjenu(5);
            s.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        String rezultat = "" + s;
        assertEquals("Učenik srednje škole Alma Almić (33221), prosjek ocjena: 4.7", rezultat);

        // Prosjek srednja
        try {
            s.dodajOcjenuSrednja(5);
            s.dodajOcjenuSrednja(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(4.5, s.prosjekSrednja());

        try {
            s.dodajOcjenuSrednja(4);
            // Ukupni prosjek je sada 4.5
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(4.5, s.prosjek());

        // Provjeravamo da li je prosjek u stringu tačan
        rezultat = "" + s;
        assertEquals("Učenik srednje škole Alma Almić (33221), prosjek ocjena: 4.5", rezultat);
    }

    @Test
    public void ilegalnaOcjena() {
        Ucenik s = new Osnovac("Sara Sarač");
        s.setBrojKnjizice("12121");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenu(6), "Ilegalna ocjena 6");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenu(11), "Ilegalna ocjena 11");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenu(-1), "Ilegalna ocjena -1");
        assertEquals(0, s.prosjek());

        // Sljedeće ocjene ne bacaju izuzetak
        try {
            s.dodajOcjenu(5);
            s.dodajOcjenu(1);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(3, s.prosjek());
    }

    @Test
    public void ilegalnaOcjenaSrednja() {
        Srednjoskolac s = new Srednjoskolac("Alma Almić");
        s.setBrojKnjizice("33221");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenuSrednja(6), "Ilegalna ocjena 6");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenuSrednja(11), "Ilegalna ocjena 11");
        assertThrows(IlegalnaOcjena.class, () -> s.dodajOcjenuSrednja(-1), "Ilegalna ocjena -1");
        assertEquals(0, s.prosjekSrednja());
        assertEquals(0, s.prosjek());

        // Sljedeće ocjene ne bacaju izuzetak
        try {
            s.dodajOcjenuSrednja(5);
            s.dodajOcjenuSrednja(1);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(3, s.prosjek());
        assertEquals(3, s.prosjekSrednja());

        try {
            s.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        String rezultat = "" + s;
        assertEquals("Učenik srednje škole Alma Almić (33221), prosjek ocjena: 3.7", rezultat);
        assertEquals(3, s.prosjekSrednja());
    }

    @Test
    public void previseOcjenaIzuzetak() {
        Ucenik s = new Osnovac("Sara Sarač");
        s.setBrojKnjizice("12121");
        try {
            for (int i=0; i<99; i++) {
                s.dodajOcjenu(5);
            }
            s.dodajOcjenu(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        assertEquals(4.99, s.prosjek());
        assertThrows(IllegalArgumentException.class, () -> s.dodajOcjenu(4), "Dosegnut maksimalan broj ocjena");
    }

    @Test
    public void testRodjendan() {
        Srednjoskolac ms = new Srednjoskolac("Sara Sarač");
        LocalDate datum = LocalDate.of(1995, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        String cestitka = ms.rodjendan(datum);
        assertEquals("Sretan rodjendan!", cestitka);

        Srednjoskolac ms2 = new Srednjoskolac("Hana Hanić");
        LocalDate datum2 = LocalDate.of(1995, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+1);
        cestitka = ms2.rodjendan(datum2);
        assertEquals("", cestitka);
    }
}