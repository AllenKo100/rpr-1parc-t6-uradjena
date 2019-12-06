package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SkolaTest {
    @Test
    public void dodavanjeOsobaRadi() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        skola.dodajOsobu(new Profesor("Nerma Nermić"));
        skola.dodajOsobu(new Osnovac("Pero Perić"));
        skola.dodajOsobu(new Srednjoskolac("Hana Hanić"));
    }

    @Test
    public void sout() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        skola.dodajOsobu(new Profesor("Nerma Nermić"));
        skola.dodajOsobu(new Osnovac("Pero Perić"));
        skola.dodajOsobu(new Srednjoskolac("Hana Hanić"));

        String rezultat = "" + skola;
        rezultat = rezultat.trim(); // Nebitno je da li na kraju ima novi red ili ne

        String ocekivani = "Učitelj Suljo Suljić\n" + "Nastavnik osnovne škole Edo Edić\n" + "Profesor Nerma Nermić\n";
        ocekivani += "Učenik osnovne škole Pero Perić (), prosjek ocjena: 0.0\n";
        ocekivani += "Učenik srednje škole Hana Hanić (), prosjek ocjena: 0.0";

        assertEquals(ocekivani, rezultat);
    }

    @Test
    public void ispisUcenikaSaProsjekom() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        Ucenik s = new Osnovac("Sara Sarač");
        s.setBrojKnjizice("23232");
        try {
            s.dodajOcjenu(3);
            s.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s);

        String rezultat = "" + skola;
        rezultat = rezultat.trim(); // Nebitno je da li na kraju ima novi red ili ne

        String ocekivani = "Učitelj Suljo Suljić\n";
        ocekivani += "Učenik osnovne škole Sara Sarač (23232), prosjek ocjena: 4.0";

        assertEquals(ocekivani, rezultat);

        // Dodajemo još neke ocjene
        try {
            s.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }

        rezultat = "" + skola;
        rezultat = rezultat.trim(); // Nebitno je da li na kraju ima novi red ili ne

        ocekivani = "Učitelj Suljo Suljić\n";
        ocekivani += "Učenik osnovne škole Sara Sarač (23232), prosjek ocjena: 4.3";

        assertEquals(ocekivani, rezultat);
    }

    @Test
    public void testUcenici() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        Ucenik s = new Osnovac("Pero Perić");
        s.setBrojKnjizice("23232");
        try {
            s.dodajOcjenu(5);
            s.dodajOcjenu(3);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s); // Pero Perić ima prosjek 4.0

        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        Srednjoskolac s2 = new Srednjoskolac("Meho Mehić");
        s2.setBrojKnjizice("12121");
        try {
            s2.dodajOcjenu(3);
            s2.dodajOcjenu(4);
            s2.dodajOcjenuSrednja(5);
            s2.dodajOcjenuSrednja(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s2); // Meho Mehić ima prosjek 4.25 zbog ocjena iz srednje

        skola.dodajOsobu(new Profesor("Nerma Nermić"));
        skola.dodajOsobu(new Osnovac("Sara Sarač")); // Sara Sarač ima prosjek 0
        Ucenik s3 = new Srednjoskolac("Hana Hanić");
        s3.setBrojKnjizice("32323");
        try {
            s3.dodajOcjenu(5);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s3); // Hana Hanić ima prosjek 5.0

        Set<Ucenik> ucenici = skola.ucenici();
        String rezultat = "";
        for (Ucenik fs : ucenici)
            rezultat += fs.getImePrezime() + " ";
        String ocekivani = "Hana Hanić Meho Mehić Pero Perić Sara Sarač ";

        assertEquals(ocekivani, rezultat);
    }


    @Test
    public void testUceniciPrazanSkup() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        skola.dodajOsobu(new Profesor("Nerma Nermić"));

        Set<Ucenik> ucenici = skola.ucenici();
        assertEquals(0, ucenici.size());

        skola.dodajOsobu(new Srednjoskolac("Hana Hanić"));

        ucenici = skola.ucenici();
        String rezultat = "";
        for (Ucenik fs : ucenici)
            rezultat += fs.getImePrezime() + " ";
        String ocekivani = "Hana Hanić ";

        assertEquals(ocekivani, rezultat);
    }

    @Test
    public void testFiltriraj() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        skola.dodajOsobu(new Profesor("Nerma Nermić"));
        skola.dodajOsobu(new Osnovac("Pero Perić"));
        skola.dodajOsobu(new Srednjoskolac("Hana Hanić"));

        List<Osoba> osobe = skola.filtriraj((Osoba o) -> o.getImePrezime().contains("o"));

        String rezultat = "";
        for (Osoba o : osobe)
            rezultat += o.getImePrezime() + " ";
        String ocekivani = "Suljo Suljić Edo Edić Pero Perić ";

        assertEquals(ocekivani, rezultat);
    }


    @Test
    public void testTopOsnovac() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new Osnovac("Jozo Jozić")); // Ne ispunjava uslov, prosjek 0

        Ucenik s = new Osnovac("Ena Enić");
        s.setBrojKnjizice("23232");
        try {
            s.dodajOcjenu(5);
            s.dodajOcjenu(3);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s); // Ispunjava uslov, prosjek 4

        Ucenik s2 = new Srednjoskolac("Dino Dinić");
        s2.setBrojKnjizice("11111");
        try {
            s2.dodajOcjenu(5);
            s2.dodajOcjenu(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s2); // Ne ispunjava uslov, srednjoškolac

        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));

        Ucenik s3 = new Osnovac("Kemo Kemić");
        s3.setBrojKnjizice("33333");
        try {
            s3.dodajOcjenu(4);
            s3.dodajOcjenu(3);
            s3.dodajOcjenu(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s3); // Ne ispunjava uslov, prosjek 3.666

        Ucenik s4 = new Osnovac("Sara Sarač");
        s4.setBrojKnjizice("33333");
        try {
            s4.dodajOcjenu(4);
            s4.dodajOcjenu(4);
            s4.dodajOcjenu(4);
        } catch (IlegalnaOcjena ilegalnaOcjena) {
            fail("Dodavanje ocjene je bacilo izuzetak IlegalnaOcjena");
        }
        skola.dodajOsobu(s4); // Ispunjava uslov, prosjek 4.0

        List<Osnovac> osobe = skola.topOsnovac();

        String rezultat = "";
        for (Osnovac bs : osobe)
            rezultat += bs.getImePrezime() + " ";
        String ocekivani = "Ena Enić Sara Sarač ";

        assertEquals(ocekivani, rezultat);
    }

    @Test
    public void testSlavljenici() {
        Skola skola = new Skola();
        skola.dodajOsobu(new Ucitelj("Suljo Suljić"));
        skola.dodajOsobu(new NastavnikOsnovneSkole("Edo Edić"));
        skola.dodajOsobu(new Profesor("Nerma Nermić"));
        skola.dodajOsobu(new Osnovac("Pero Perić"));
        skola.dodajOsobu(new Srednjoskolac("Hana Hanić"));

        List<Slavljenik> slavljenik = skola.slavljenici();

        String rezultat = "";
        for (Slavljenik s : slavljenik) {
            Osoba o = (Osoba) s;
            rezultat += o.getImePrezime() + " ";
        }
        String ocekivani = "Suljo Suljić Hana Hanić ";

        assertEquals(ocekivani, rezultat);
    }
}