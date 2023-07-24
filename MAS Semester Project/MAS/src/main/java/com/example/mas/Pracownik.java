package com.example.mas;

import java.util.List;

public abstract class Pracownik extends Regal {
    private Osoba osoba;

    protected boolean badaniaLekarskie;
    protected float pensja;

    protected Biblioteka biblioteka;
    public Pracownik(boolean badaniaLekarskie, float pensja) {
        this.badaniaLekarskie = badaniaLekarskie;
        this.pensja = pensja;
        this.osoba = osoba;
    }

    public abstract void wykonajZadanie();

    public void ulozKsiazki(Regal[] regaly, List<Ksiazki> ksiazki) {
        int liczbaRegalow = regaly.length;
        int indexRegalu = 0;

        for (Ksiazki ksiazka : ksiazki) {
            Regal regal = regaly[indexRegalu];

            regal.dodajKsiazke(ksiazka);

            indexRegalu++;
            if (indexRegalu >= liczbaRegalow) {
                indexRegalu = 0;
            }
        }
    }

    public abstract void badaniaLekarskie();

    public abstract void obliczPensje();

    public Biblioteka getBiblioteka() {
        return biblioteka;
    }

    public void setBiblioteka(Biblioteka biblioteka) {
        this.biblioteka = biblioteka;
    }
}
