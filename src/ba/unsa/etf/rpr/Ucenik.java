package ba.unsa.etf.rpr;

public class Ucenik extends Osoba implements Comparable<Ucenik> {
    private String brojKnjizice = "";
    private int[] ocjene = new int[100];
    private int brOcjena = 0;

    public Ucenik(String imePrezime) {
        super(imePrezime);
    }

    public String getBrojKnjizice() {
        return brojKnjizice;
    }

    public void setBrojKnjizice(String brojKnjizice) {
        this.brojKnjizice = brojKnjizice;
    }

    public int getBrOcjena() {
        return brOcjena;
    }

    public void dodajOcjenu(int ocjena) throws IlegalnaOcjena {
        if(ocjena < 1 || ocjena > 5)
            throw new IlegalnaOcjena("Ilegalna ocjena " + Integer.toString(ocjena));
        if(brOcjena >= 100)
            throw new IllegalArgumentException("Dosegnut maksimalan broj ocjena");

        ocjene[brOcjena++] = ocjena;
    }

    public double prosjek() {
        if(brOcjena==0)
            return 0;

        double sum = 0;

        for(int i = 0; i < brOcjena; i++){
            sum += ocjene[i];
        }
        return (sum/brOcjena);
    }

    @Override
    public int compareTo(Ucenik u){
        if(prosjek() < u.prosjek()) return 1;
        if(prosjek() > u.prosjek()) return -1;
        return 0;
    }

    @Override
    public String toString(){
        String povratni = getImePrezime() + " (" + getBrojKnjizice() + "), prosjek ocjena: ";
        double pr = Math.round(prosjek() * 10);
        pr = pr/10;
        povratni = povratni + pr;

        return povratni;
    }
}
