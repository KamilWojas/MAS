package com.example.mas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KatalogBiblioteki extends Stage {
    private Runnable returnAction;
    private Czytelnik czytelnik;

    public void setCzytelnik(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
    }

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    public KatalogBiblioteki(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
        setTitle("Katalog Biblioteki");

        Label titleLabel = new Label("Dostępne książki:");

        ListView<String> bookListView = new ListView<>();
        List<Ksiazki> ksiazki = pobierzKsiazkiZBazyDanych();
        for (Ksiazki ksiazka : ksiazki) {
            bookListView.getItems().add(ksiazka.getTytul() + " - " + ksiazka.getAutor());
        }
        bookListView.setOnMouseClicked(event -> {
            String selectedItem = bookListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Przejście do szczegółowego widoku książki
                SzczegolyKsiazki szczegolyKsiazki = new SzczegolyKsiazki(selectedItem, true);
                szczegolyKsiazki.show();
            }
        });

        Button backButton = new Button("Powrót");
        backButton.setOnAction(e -> {
            if (returnAction != null) {
                returnAction.run();
            }
            close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, bookListView, backButton);

        setScene(new Scene(layout, 300, 300));
    }

    private List<Ksiazki> pobierzKsiazkiZBazyDanych() {
        List<Ksiazki> ksiazki = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "SELECT * FROM ksiazki";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tytul = resultSet.getString("tytul");
                String autor = resultSet.getString("autor");
                int rokWydania = resultSet.getInt("rok_wydania");

                Ksiazki ksiazka = new Ksiazki(tytul, autor, rokWydania);
                ksiazki.add(ksiazka);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Błąd podczas pobierania książek z bazy danych.");
        }

        return ksiazki;
    }
    private String[] pobierzWypozyczoneKsiazki() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "SELECT tytul FROM Wypozyczenie WHERE czytelnik_id = ?";
            // nowe query dla wypozyczonych (where kategoria_id = 1)
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, czytelnik.getId()); // ID czytelnika
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> borrowedBooksList = new ArrayList<>();
            while (resultSet.next()) {
                borrowedBooksList.add(resultSet.getString("tytul"));
            }

            connection.close();

            String[] borrowedBooks = new String[borrowedBooksList.size()];
            borrowedBooks = borrowedBooksList.toArray(borrowedBooks);
            return borrowedBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[0];
    }
}
