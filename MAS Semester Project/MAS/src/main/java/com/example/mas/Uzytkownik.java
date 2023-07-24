package com.example.mas;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Uzytkownik implements Serializable {
    private static final Collection<Uzytkownik> ALL_UZYTKOWNICY = new ArrayList<>();

    private String nazwaUzytkownika;
    private String haslo;

    public Uzytkownik(String nazwaUzytkownika, String haslo) {
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.haslo = haslo;
        ALL_UZYTKOWNICY.add(this);
    }

    public Uzytkownik() {

    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public abstract void zarejestruj(); // metoda abstrakcyjna do implementacji w klasach pochodnych

    public static void writeExtent(final ObjectOutputStream oos) throws Exception {
        oos.writeObject(ALL_UZYTKOWNICY);
    }

    public static Collection<Uzytkownik> readExtent(final ObjectInputStream oos) throws Exception {
        return (Collection<Uzytkownik>) oos.readObject();
    }
}
