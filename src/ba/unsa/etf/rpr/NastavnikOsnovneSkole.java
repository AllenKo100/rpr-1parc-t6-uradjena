package ba.unsa.etf.rpr;

public class NastavnikOsnovneSkole extends Nastavnik {
    public NastavnikOsnovneSkole(String imePrezime){
        super(imePrezime);
    }

    @Override
    public String toString() {
        String povratni = "Nastavnik osnovne škole " + this.getImePrezime();

        return povratni;
    }
}
