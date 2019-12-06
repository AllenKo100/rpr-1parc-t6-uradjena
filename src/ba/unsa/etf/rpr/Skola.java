package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Skola {
    private List<Osoba> osobe = new ArrayList<>();

    public void dodajOsobu(Osoba o) {
        osobe.add(o);
    }

    public Set<Ucenik> ucenici() {
        TreeSet<Ucenik> povratni = new TreeSet<>();

        for(Osoba o : osobe){
            if(o instanceof Ucenik)
                povratni.add((Ucenik) o);
        }

        return povratni;
    }

    List<Osoba> filtriraj(Function<Osoba, Boolean> fun) {
        ArrayList<Osoba> povratni = new ArrayList<>();

        for(Osoba o : osobe){
            if(fun.apply(o))
                povratni.add(o);
        }

        return povratni;
    }

    public List<Osnovac> topOsnovac() {
        List povratna = filtriraj((Osoba o) -> { return (o instanceof Osnovac && ((Osnovac) o).prosjek() >= 4);});
        return povratna;
    }

    @Override
    public String toString() {
        String povratni = "";

        for(Osoba o : osobe){
            povratni = povratni + o + "\n";
        }

        return povratni;
    }

    public List<Slavljenik> slavljenici() {
        List<Slavljenik> povratni = new ArrayList<>();

        for(Osoba o : osobe){
            if(o instanceof Srednjoskolac || o instanceof Ucitelj){
                povratni.add((Slavljenik) o);
            }
        }

        return povratni;
    }
}
