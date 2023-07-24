package com.example.mas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Biblioteka {
    private Map<Czytelnik, Integer> wypozyczenia;
    private List<Regal> regaly;
    private Pracownik pracownik;

    public Biblioteka() {
        this.regaly = new ArrayList<>();
        wypozyczenia = new HashMap<>();
    }

    public void dodajRegal(Regal regal) {
        regaly.add(regal);
        regal.setBiblioteka(this); // Połączenie zwrotne: ustawienie biblioteki dla regału
    }

    public void usunRegal(Regal regal) {
        regaly.remove(regal);
        regal.setBiblioteka(null); // Odłączenie regału od biblioteki
    }

    public Regal znajdzRegal(String nazwa) {
        for (Regal regal : regaly) {
            if (regal.getNazwa().equals(nazwa)) {
                return regal;
            }
        }
        return null;
    }
    public Pracownik getPracownik(){
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik){
        this.pracownik = pracownik;
    }


    public void wypozyczKsiazke(Czytelnik czytelnik, Ksiazka ksiazka) {
        int liczbaWypozyczen = wypozyczenia.getOrDefault(czytelnik, 0);
        wypozyczenia.put(czytelnik, liczbaWypozyczen + 1);
        ksiazka.setWypozyczona(true);
        czytelnik.dodajWypozyczenie(ksiazka);
    }

    public void zwrocKsiazke(Czytelnik czytelnik, Ksiazka ksiazka) {
        int liczbaWypozyczen = wypozyczenia.getOrDefault(czytelnik, 0);
        if (liczbaWypozyczen > 0) {
            wypozyczenia.put(czytelnik, liczbaWypozyczen - 1);
            ksiazka.setWypozyczona(false);
            czytelnik.usunWypozyczenie(ksiazka);
        }
    }
}

