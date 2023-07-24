package com.example.mas;
abstract class Ksiazka {
    private int id;
    private String tytul;
    private String autor;
    private int rokWydania;
    private Regal regal;
    private boolean wypozyczona;

    public Ksiazka(int id, String tytul, String autor, int rokWydania) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public Regal getRegal() {
        return regal;
    }

    public boolean isWypozyczona() {
        return wypozyczona;
    }

    public void setWypozyczona(boolean wypozyczona) {
        this.wypozyczona = wypozyczona;
    }

    public abstract void dodajKsiazke();

    public abstract void dodajKsiazke(String miejscePrzechowywania);

    public abstract void dodajKsiazke(int liczbaEgzemplarzy);

    protected void dodajDoBazyDanych() {
        // Logika dodawania do bazy danych
    }
}

class KsiazkaRodzaj extends Ksiazka {
    private String rodzaj;

    public KsiazkaRodzaj(int id, String tytul, String autor, int rokWydania, String rodzaj) {
        super(id, tytul, autor, rokWydania);
        this.rodzaj = rodzaj;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Override
    public void dodajKsiazke() {
        dodajDoBazyDanych();
        System.out.println("Dodano książkę: " + getTytul() + " - " + rodzaj);
    }

    @Override
    public void dodajKsiazke(String miejscePrzechowywania) {
        dodajDoBazyDanych();
        System.out.println("Dodano książkę: " + getTytul() + " - " + miejscePrzechowywania);
    }

    @Override
    public void dodajKsiazke(int liczbaEgzemplarzy) {
        dodajDoBazyDanych();
        System.out.println("Dodano książkę: " + getTytul() + " - " + liczbaEgzemplarzy + " egzemplarzy");
    }
}

class Czasopismo extends Ksiazka {
    private String temat;

    public Czasopismo(int id, String tytul, String autor, int rokWydania, String temat) {
        super(id, tytul, autor, rokWydania);
        this.temat = temat;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    @Override
    public void dodajKsiazke() {
        dodajDoBazyDanych();
        System.out.println("Dodano czasopismo: " + getTytul() + " - " + temat);
    }

    @Override
    public void dodajKsiazke(String miejscePrzechowywania) {
        dodajDoBazyDanych();
        System.out.println("Dodano czasopismo: " + getTytul() + " - " + miejscePrzechowywania);
    }

    @Override
    public void dodajKsiazke(int liczbaEgzemplarzy) {
        dodajDoBazyDanych();
        System.out.println("Dodano czasopismo: " + getTytul() + " - " + liczbaEgzemplarzy + " egzemplarzy");
    }
}
/*
public class Main {
    public static void main(String[] args) {
        KsiazkaRodzaj ksiazka = new KsiazkaRodzaj(1, "Władca Pierścieni", "J.R.R. Tolkien", 1954, "Fantasy");
        ksiazka.dodajKsiazke();

        ksiazka.dodajKsiazke("Półka 3");

        ksiazka.dodajKsiazke(5);

        Czasopismo czasopismo = new Czasopismo(2, "National Geographic", "Various Authors", 2023, "Nature");
        czasopismo.dodajKsiazke();

        czasopismo.dodajKsiazke("Stolik kawowy");

        czasopismo.dodajKsiazke(3);
    }*/

