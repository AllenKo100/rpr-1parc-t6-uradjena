package ba.unsa.etf.rpr;

import java.time.LocalDate;

public class Srednjoskolac extends Ucenik implements Slavljenik {
    private int[] ocjeneSrednja = new int[100];
    private int brOcjenaSrednja = 0;

    public Srednjoskolac(String imePrezime){
        super(imePrezime);
    }

    public int getBrOcjenaSrednja() {
        return brOcjenaSrednja;
    }

    public void dodajOcjenuSrednja(int ocjena) throws IlegalnaOcjena {
        if(ocjena < 1 || ocjena > 5)
            throw new IlegalnaOcjena("Ilegalna ocjena " + Integer.toString(ocjena));
        if(brOcjenaSrednja >= 100)
            throw new IllegalArgumentException("Dosegnut maksimalan broj ocjena");

        ocjeneSrednja[brOcjenaSrednja++] = ocjena;
    }

    public double prosjekSrednja() {
        if(brOcjenaSrednja == 0)
            return 0;

        double sum = 0;

        for(int i = 0; i < brOcjenaSrednja; i++){
            sum = sum + ocjeneSrednja[i];
        }

        return (sum/brOcjenaSrednja);
    }

    @Override
    public double prosjek() {
        if(getBrOcjena() == 0 && getBrOcjenaSrednja() == 0)
            return 0;

        double sum = super.prosjek() * getBrOcjena();

        for(int i = 0; i < brOcjenaSrednja; i++){
            sum = sum + ocjeneSrednja[i];
        }

        return (sum / (getBrOcjena() + getBrOcjenaSrednja()));
    }

    @Override
    public String toString() {
        String povratni = "Učenik srednje škole " + super.toString();

        return povratni;
    }
}
