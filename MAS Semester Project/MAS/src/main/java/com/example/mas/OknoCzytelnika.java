package com.example.mas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OknoCzytelnika extends Stage {
    private Runnable returnAction;
    private Czytelnik czytelnik;
    private List<Ksiazki> wypozyczoneKsiazki;

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    public void setCzytelnik(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
    }

    public void setWypozyczoneKsiazki(List<Ksiazki> wypozyczoneKsiazki) {
        this.wypozyczoneKsiazki = wypozyczoneKsiazki;
    }

    public OknoCzytelnika() {
        setTitle("Okno Czytelnika");

        Label label = new Label("Witaj, Czytelniku!");
        label.setAlignment(Pos.CENTER);

        Button logoutButton = new Button("Wyloguj");
        logoutButton.setOnAction(e -> {
            if (returnAction != null) {
                returnAction.run();
            }
            close();
        });
        logoutButton.getStyleClass().add("logout-button");

        Button borrowedBooksButton = new Button("Wypożyczone książki");
        borrowedBooksButton.setOnAction(e -> showBorrowedBooksWindow());
        borrowedBooksButton.getStyleClass().add("custom-button");

        Button catalogButton = createCatalogButton();
        catalogButton.getStyleClass().add("custom-button");

        Button searchButton = createSearchButton();
        searchButton.getStyleClass().add("custom-button");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, new HBox(10, catalogButton, searchButton), new HBox(10, borrowedBooksButton));

        HBox buttonBox = new HBox(logoutButton);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.setPadding(new Insets(10));

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(layout);
        rootPane.setBottom(buttonBox);

        Scene scene = new Scene(rootPane, 300, 200);
        scene.getStylesheets().add("styles.css");

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Button focusedButton = (Button) scene.getFocusOwner();
                if (focusedButton != null) {
                    focusedButton.fire();
                }
            }
        });

        setScene(scene);
    }

    private Button createCatalogButton() {
        Button catalogButton = new Button("Przeglądaj katalog");
        catalogButton.setOnAction(e -> showCatalogWindow());
        return catalogButton;
    }

    private Button createSearchButton() {
        Button searchButton = new Button("Wyszukaj książkę");
        searchButton.setOnAction(e -> showSearchWindow());
        return searchButton;
    }

    private void showCatalogWindow() {
        KatalogBiblioteki katalogBiblioteki = new KatalogBiblioteki(czytelnik);
        katalogBiblioteki.setReturnAction(() -> show());
        katalogBiblioteki.setCzytelnik(czytelnik);
        katalogBiblioteki.show();
        hide();
    }

    private void showSearchWindow() {
        WyszukiwanieKsiazki wyszukiwanieKsiazki = new WyszukiwanieKsiazki();
        wyszukiwanieKsiazki.setReturnAction(() -> show());
        wyszukiwanieKsiazki.show();
        hide();
    }

   /* public void showBorrowedBooksWindow() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "SELECT tytul FROM wypozyczenie WHERE czytelnik_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, czytelnik.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Ksiazki> borrowedBooks = new ArrayList<>();
            while (resultSet.next()) {
                String tytul = resultSet.getString("tytul");
                borrowedBooks.add(new Ksiazki(tytul, "", 0));
            }

            setWypozyczoneKsiazki(borrowedBooks);

            OknoWypozyczonychKsiazek okno = new OknoWypozyczonychKsiazek(borrowedBooks);
            okno.setReturnAction(() -> {
                setWypozyczoneKsiazki(borrowedBooks); // Aktualizuj listę wypożyczonych książek po zamknięciu okna wypożyczonych książek
                show();
            });
            okno.show();
            hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/


       /* List<Ksiazki> borrowedBooks = new ArrayList<>();
        //nowe query dla wypozyczonych
        borrowedBooks.add(new Ksiazki("Harry Potter i Kamien Filozoficzny", " J.K. Rowling", 1997));
        borrowedBooks.add(new Ksiazki("Harry Potter i Komnata Tajemnic", "J.K. Rowling", 1998));
        borrowedBooks.add(new Ksiazki("Harry Potter i więzień Azkabanu ", "J.K. Rowling", 1999));
        borrowedBooks.add(new Ksiazki("Władca Pierścieni: Drużyna Pierścienia", "J.R.R. Tolkien", 2001));


        OknoWypozyczonychKsiazek okno = new OknoWypozyczonychKsiazek(borrowedBooks);
        okno.setReturnAction(() -> show());
        okno.show();
        hide();*/


    /*private void showBorrowedBooksWindow() {
        if (czytelnik == null) {
            System.out.println("Obiekt czytelnik jest null. Brak informacji o wypożyczonych książkach.");
            return;
        }

        List<Ksiazki> borrowedBooks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!")) {
            String query = "SELECT tytul, autor, rok_wydania FROM wypozyczenia WHERE wypozyczenie_aktivne = 1 AND czytelnik_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, czytelnik.getId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String title = resultSet.getString("tytul");
                        String author = resultSet.getString("autor");
                        int year = resultSet.getInt("rok_wydania");

                        Ksiazki book = new Ksiazki(title, author, year);
                        borrowedBooks.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OknoWypozyczonychKsiazek okno = new OknoWypozyczonychKsiazek(borrowedBooks);
        okno.setReturnAction(() -> show());
        okno.show();
        hide();
    }*/
    void showBorrowedBooksWindow() {
        List<Ksiazki> borrowedBooks = new ArrayList<>();
        //nowe query dla wypozyczonych
         borrowedBooks.add(new Ksiazki("Harry Potter i Kamien Filozoficzny", " J.K. Rowling", 1997));
        borrowedBooks.add(new Ksiazki("Harry Potter i Komnata Tajemnic", "J.K. Rowling", 1998));
        borrowedBooks.add(new Ksiazki("Harry Potter i więzień Azkabanu ", "J.K. Rowling", 1999));
         borrowedBooks.add(new Ksiazki("Władca Pierścieni: Drużyna Pierścienia", "J.R.R. Tolkien", 2001));


        OknoWypozyczonychKsiazek okno = new OknoWypozyczonychKsiazek(borrowedBooks);
        okno.setReturnAction(() -> show());
        okno.show();
        hide();
    }
}


