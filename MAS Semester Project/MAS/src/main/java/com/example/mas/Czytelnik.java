package com.example.mas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Czytelnik<Biblioteka> extends Uzytkownik {
    private static List<Czytelnik> kontenerCzytelnikow = new ArrayList<>();
    private static int counter = 0;
    private static final String DEFAULT_NAZWA = "Czytelnik";
    private static int nextId = 1; // Atrybut klasowy nextId
    private Map<Ksiazka, Integer> wypozyczenia;
    private int id;
    private List<Wypozyczenie> wypozyczoneKsiazki;
    private String imie;
    private Adres adres;
    private String adresEmail;
    private String nazwiskoPanienskie;
    private List<Ksiazki> poprzednioWypozyczoneKsiazki;
    private Biblioteka biblioteka;
    private String nazwa;

    public Czytelnik(String imie, String nazwaUzytkownika, String haslo, Adres adres, String adresEmail) {
        super(nazwaUzytkownika, haslo);
        this.id = generateUniqueId();
        this.imie = imie;
        this.adres = adres;
        this.adresEmail = adresEmail;
        this.nazwiskoPanienskie = null;
        this.wypozyczoneKsiazki = new ArrayList<>();
        this.poprzednioWypozyczoneKsiazki = new ArrayList<>();
        this.nazwa = DEFAULT_NAZWA;
        wypozyczenia = new HashMap<>();


        kontenerCzytelnikow.add(this);
    }

    public Czytelnik() {
        super();
    }

    public String getNazwa() {
        return nazwa;
    }


    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    public String getNazwiskoPanienskie() {
        return nazwiskoPanienskie;
    }

    public void setNazwiskoPanienskie(String nazwiskoPanienskie) {
        this.nazwiskoPanienskie = nazwiskoPanienskie;
    }

    public List<Ksiazki> getPoprzednioWypozyczoneKsiazki() {
        return poprzednioWypozyczoneKsiazki;
    }

    public void setPoprzednioWypozyczoneKsiazki(List<Ksiazki> poprzednioWypozyczoneKsiazki) {
        this.poprzednioWypozyczoneKsiazki = poprzednioWypozyczoneKsiazki;
    }

    public Biblioteka getBiblioteka() {
        return biblioteka;
    }

    public void setBiblioteka(Biblioteka biblioteka) {
        this.biblioteka = biblioteka;
    }

    public void wypozyczKsiazke(Ksiazki ksiazka, LocalDate dataWypozyczenia) {
        Wypozyczenie wypozyczenie = new Wypozyczenie(ksiazka, dataWypozyczenia);
        wypozyczenie.setCzytelnik(this);
        wypozyczoneKsiazki.add(wypozyczenie);
        ksiazka.setCzytelnik(this);
    }

    public List<Wypozyczenie> getWypozyczoneKsiazki() {
        List<Wypozyczenie> wypozyczoneKsiazki = new ArrayList<>();
        for (Wypozyczenie wypozyczenie : this.wypozyczoneKsiazki) {
            if (!wypozyczenie.isZwrocona()) {
                wypozyczoneKsiazki.add(wypozyczenie);
            }
        }
        return wypozyczoneKsiazki;
    }

    public void zwrotKsiazki(Ksiazki ksiazka) {
        for (Wypozyczenie wypozyczenie : wypozyczoneKsiazki) {
            if (wypozyczenie.getKsiazka().equals(ksiazka)) {
                wypozyczenie.setZwrocona(true);
                wypozyczenie.setCzytelnik(null);
                ksiazka.setCzytelnik(null);
                wypozyczoneKsiazki.remove(wypozyczenie);
                poprzednioWypozyczoneKsiazki.add(wypozyczenie.getKsiazka());
                break;
            }
        }
    }
    public void dodajWypozyczenie(Ksiazka ksiazka) {
        int liczbaWypozyczen = wypozyczenia.getOrDefault(ksiazka, 0);
        wypozyczenia.put(ksiazka, liczbaWypozyczen + 1);
    }

    public void usunWypozyczenie(Ksiazka ksiazka) {
        int liczbaWypozyczen = wypozyczenia.getOrDefault(ksiazka, 0);
        if (liczbaWypozyczen > 0) {
            wypozyczenia.put(ksiazka, liczbaWypozyczen - 1);
        }
    }

    private Ksiazki znajdzWypozyczenie(Ksiazki ksiazka) {
        for (Ksiazki wypozyczenie : poprzednioWypozyczoneKsiazki) {
            if (wypozyczenie.getKsiazka().equals(ksiazka)) {
                return wypozyczenie;
            }
        }
        return null;
    }



    @Override
    public void zarejestruj() {
    }

    @Override
    public String toString() {
        return "Czytelnik{" +
                "imie='" + imie + '\'' +
                ", adres=" + adres +
                ", adresEmail='" + adresEmail + '\'' +
                ", nazwiskoPanienskie='" + nazwiskoPanienskie + '\'' +
                ", poprzednioWypozyczoneKsiazki=" + poprzednioWypozyczoneKsiazki +
                '}';
    }

    private class Wypozyczenie {
        private Ksiazki ksiazka;
        private LocalDate dataWypozyczenie;
        private boolean zwrocona;
        private Czytelnik czytelnik;

        public Wypozyczenie(Ksiazki ksiazka, LocalDate dataWypozyczenie) {
            this.ksiazka = ksiazka;
            this.dataWypozyczenie = dataWypozyczenie;
            this.zwrocona = false;
            this.czytelnik = null;
        }

        public Ksiazki getKsiazka() {
            return ksiazka;
        }

        public void setKsiazka(Ksiazki ksiazka) {
            this.ksiazka = ksiazka;
        }

        public LocalDate getDataWypozyczenie() {
            return dataWypozyczenie;
        }

        public void setDataWypozyczenie(LocalDate dataWypozyczenie) {
            this.dataWypozyczenie = dataWypozyczenie;
        }

        public boolean isZwrocona() {
            return zwrocona;
        }

        public void setZwrocona(boolean zwrocona) {
            this.zwrocona = zwrocona;
        }

        public Czytelnik getCzytelnik() {
            return czytelnik;
        }

        public void setCzytelnik(Czytelnik czytelnik) {
            this.czytelnik = czytelnik;
        }
    }

    private static int generateUniqueId() {
        return counter++;
    }
}