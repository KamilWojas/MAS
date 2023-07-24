package com.example.mas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ksiazki {
    private int id;
    private String tytul;
    private String autor;
    private int rokWydania;
    private static int iloscKsiazek = 0;
    private Czytelnik czytelnik;
    private Regal regal;
    private boolean dostepna;
    private String wypozyczajacy;
    private String ISBN;


    public Ksiazki(String tytul, String autor, int rokWydania) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
        iloscKsiazek++;
    }


    public boolean isDostepna() {
        return dostepna;
    }

    public void setDostepna(boolean dostepna) {
        this.dostepna = dostepna;
    }

    public String getWypozyczajacy() {
        return wypozyczajacy;
    }

    public void setWypozyczajacy(String wypozyczajacy) {
        this.wypozyczajacy = wypozyczajacy;
    }

    @Override
    public String toString() {
        return tytul;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getAutor() {
        return autor;
    }

    public Czytelnik getCzytelnik() {
        return czytelnik;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public void setCzytelnik(Czytelnik czytelnik){
        this.czytelnik = czytelnik;
    }
    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }

    public String getKsiazka() {
        return "Tytuł: " + tytul + ", Autor: " + autor + ", Rok wydania: " + rokWydania;
    }

    public void dodajDoBazyDanych() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "INSERT INTO Ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tytul);
            preparedStatement.setString(2, autor);
            preparedStatement.setInt(3, rokWydania);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Błąd podczas dodawania książki do bazy danych.");
        }
    }

    public static Ksiazki findOldestBook(List<Ksiazki> books) {
        return books.stream()
                .min(Comparator.comparingInt(Ksiazki::getRokWydania))
                .orElse(null);
    }

    public static void wyswietlIloscKsiazek() {
        System.out.println("Ilość książek: " + iloscKsiazek);
    }

    public Regal getRegal() {
        return regal;
    }

    public void setRegal(Regal regal) {
        this.regal = regal;
    }

    public static void main(String[] args) {
        List<Ksiazki> books = new ArrayList<>();
        books.add(new Ksiazki("Tytuł książki 1", "Autor książki 1", 2022));
        books.add(new Ksiazki("Tytuł książki 2", "Autor książki 2", 2023));
        books.add(new Ksiazki("Tytuł książki 3", "Autor książki 3", 2024));
        books.add(new Ksiazki("Tytuł książki 4", "Autor książki 4", 2025));
        books.add(new Ksiazki("Tytuł książki 5", "Autor książki 5", 2026));

        Ksiazki oldestBook = Ksiazki.findOldestBook(books);
        if (oldestBook != null) {
            System.out.println("Najstarsza książka: " + oldestBook.getTytul());
        } else {
            System.out.println("Brak książek na liście.");
        }
    }
}


