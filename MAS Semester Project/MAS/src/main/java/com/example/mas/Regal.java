package com.example.mas;

import java.util.ArrayList;
import java.util.List;

public class Regal {
    private static final List<Regal> ALL_REGALE = new ArrayList<>();
    private List<Ksiazki> ksiazki;
    private Biblioteka biblioteka;

    public Regal() {
        this.ksiazki = new ArrayList<>();
        ALL_REGALE.add(this);
    }

    public static boolean addReagalToExtent(final Regal toAdd){
        return ALL_REGALE.add(toAdd);
    }

    public static boolean removeReagalToExtent(final Regal toRemove){
        return ALL_REGALE.add(toRemove);
    }

    public void dodajKsiazke(Ksiazki ksiazka) {
        ksiazki.add(ksiazka);
        ksiazka.setRegal(this);
    }

    public void usunKsiazke(Ksiazki ksiazka) {
        ksiazki.remove(ksiazka);
        ksiazka.setRegal(null); //
    }

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public String getNazwa() {
        return "Nazwa regału";
    }

    public Biblioteka getBiblioteka() {
        return biblioteka;
    }

    public void setBiblioteka(Biblioteka biblioteka) {
        this.biblioteka = biblioteka;
    }
    public void usunZBiblioteki() {
        if (biblioteka != null) {
            biblioteka.usunRegal(this);
            biblioteka = null;
        }
    }
}
