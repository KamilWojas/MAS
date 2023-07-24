package com.example.mas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bibliotekarz extends Pracownik {
    private Map<Float, List<InfoBibliotekarz>> bibliotekarze;
    private InfoBibliotekarz infoBibliotekarz;
    private String login;
    private Pracownik pracownik;

    public Bibliotekarz(boolean badaniaLekarskie, float pensja) {
        super(badaniaLekarskie, pensja);
        bibliotekarze = new HashMap<>();
        this.pracownik = pracownik;
    }

    public void ukladajKsiazki(Regal regal, List<Ksiazki> ksiazki) {
        for (Ksiazki ksiazka : ksiazki) {
            regal.dodajKsiazke(ksiazka);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setInfoBibliotekarz(InfoBibliotekarz infoBibliotekarz) {
        this.infoBibliotekarz = infoBibliotekarz;
    }


    public int podejrzyjWypozyczoneKsiazki() {
        List<Ksiazki> wypozyczoneKsiazki = (List<Ksiazki>) getBiblioteka();

        if (wypozyczoneKsiazki.isEmpty()) {
            System.out.println("Czytelnik nie ma wypożyczonych książek.");
        } else {
            System.out.println("Wypożyczone książki przez czytelnika:");
            for (Ksiazki ksiazka : wypozyczoneKsiazki) {
                System.out.println(ksiazka.getTytul());
            }
        }
        return 0;
    }

    public void dodajBibliotekarza(float pensja, Bibliotekarz bibliotekarz, String specjalizacja) {
        if (!bibliotekarze.containsKey(pensja)) {
            bibliotekarze.put(pensja, new ArrayList<>());
        }
        InfoBibliotekarz infoBibliotekarz = new InfoBibliotekarz(bibliotekarz, specjalizacja);
        bibliotekarze.get(pensja).add(infoBibliotekarz);
        infoBibliotekarz.getBibliotekarz().setInfoBibliotekarz(infoBibliotekarz); // Połączenie zwrotne

    }

    public List<Bibliotekarz> znajdzBibliotekarzy(float pensja, String specjalizacja) {
        List<Bibliotekarz> znalezieniBibliotekarze = new ArrayList<>();
        List<InfoBibliotekarz> lista = bibliotekarze.getOrDefault(pensja, new ArrayList<>());
        for (InfoBibliotekarz infoBibliotekarz : lista) {
            if (infoBibliotekarz.getSpecjalizacja().equals(specjalizacja)) {
                znalezieniBibliotekarze.add(infoBibliotekarz.getBibliotekarz());
            }
        }
        return znalezieniBibliotekarze;
    }

    static class BibliotekarzPoczatkujacy extends Bibliotekarz {
        public BibliotekarzPoczatkujacy(boolean badaniaLekarskie, float pensja) {
            super(badaniaLekarskie, pensja);
        }

        @Override
        public void ukladajKsiazki(Regal regal, List<Ksiazki> ksiazki) {
            System.out.println("Bibliotekarz początkujący układa książki na regale.");
            super.ukladajKsiazki(regal, ksiazki);
        }
    }

    static class BibliotekarzDoswiadczony extends Bibliotekarz {
        public BibliotekarzDoswiadczony(boolean badaniaLekarskie, float pensja) {
            super(badaniaLekarskie, pensja);



        }

        @Override
        public void ukladajKsiazki(Regal regal, List<Ksiazki> ksiazki) {
            System.out.println("Bibliotekarz doświadczony układa książki na regale w sposób zaawansowany.");
            super.ukladajKsiazki(regal, ksiazki);
        }
    }

    @Override
    public void wykonajZadanie() {
    }

    @Override
    public void badaniaLekarskie() {

    }

    @Override
    public void obliczPensje() {

    }
}
