package com.example.mas;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract class Osoba extends Pracownik {
    protected Collection<String> imiona;
    protected String nazwisko;
    protected List<String> adresy;
    protected Date dataUrodzenia;
    protected Collection<String> telefony;
    private static final int MAX_LICZBA_KONT = 1;
    private static int liczbaKontUtworzonych = 0;


    public Osoba(Pracownik poprzedniPracownik, Collection<String> imiona, String nazwisko, List<String> adresy, Date dataUrodzenia, Collection<String> telefony) {
        super(poprzedniPracownik.badaniaLekarskie, poprzedniPracownik.pensja);
        this.imiona = new ArrayList<>(imiona);
        this.nazwisko = nazwisko;
        this.adresy = new ArrayList<>(adresy);
        this.telefony = new ArrayList<>(telefony);
        this.dataUrodzenia = dataUrodzenia;

        if (liczbaKontUtworzonych < MAX_LICZBA_KONT) {
            liczbaKontUtworzonych++;
        } else {
            throw new IllegalStateException("Przekroczono maksymalną liczbę kont dla użytkownika.");
        }

    }

    public void dodajAdres(String adres) {
        adresy.add(adres);
    }

    public void usunAdres(String adres) {
        adresy.remove(adres);
    }

    public List<String> getAdresy() {
        return adresy;
    }

    public int getAge() {
        LocalDate dataUrodzeniaLocal = dataUrodzenia.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dzis = LocalDate.now();
        Period okres = Period.between(dataUrodzeniaLocal, dzis);
        return okres.getYears();

    }

    public int getLiczbaKontUtworzonych() {
        return liczbaKontUtworzonych;
    }


    @Override
    public void badaniaLekarskie() {
        System.out.println("Wykonuję badania lekarskie dla osoby: " + imiona + " " + nazwisko);
    }

    @Override
    public void obliczPensje() {
        System.out.println("Obliczam pensję dla osoby: " + imiona + " " + nazwisko);
    }

    @Override
    public String toString() {
        return imiona + " " + nazwisko;
    }
}
