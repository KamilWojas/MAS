package com.example.mas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;

public class OknoWypozyczonychKsiazek extends Stage {
    private Runnable returnAction;
    private ObservableList<Ksiazki> borrowedBooksList;
    private int borrowLimit = 5;

    public OknoWypozyczonychKsiazek(List<Ksiazki> borrowedBooks) {
        setTitle("Wypożyczone książki");

        Label label = new Label("Twoje wypożyczone książki:");
        Label limitLabel = new Label("Limit wypożyczeń: " + borrowLimit);
        Label countLabel = new Label("Liczba wypożyczonych książek: " + borrowedBooks.size());

        ListView<Ksiazki> listView = new ListView<>();
        borrowedBooksList = FXCollections.observableArrayList(borrowedBooks);
        listView.setItems(borrowedBooksList);

        Button returnButton = new Button("Powrót");
        returnButton.setOnAction(e -> {
            if (returnAction != null) {
                returnAction.run();
            }
            close();
        });

        Button zwrocButton = new Button("Zwrot");
        zwrocButton.setOnAction(e -> {
            Ksiazki selectedBook = listView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                zwrocKsiazke(selectedBook);
                borrowedBooksList.remove(selectedBook);
                countLabel.setText("Liczba wypożyczonych książek: " + borrowedBooksList.size());
            } else {
                System.out.println("Wybierz książkę do zwrotu.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label, listView, limitLabel, countLabel, zwrocButton, returnButton);

        setScene(new Scene(layout, 300, 200));
    }

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    private void zwrocKsiazke(Ksiazki selectedBook) {
        System.out.println("Zwrot książki: " + selectedBook.getTytul());

        int aktualnaLiczbaEgzemplarzy = pobierzLiczbeEgzemplarzy(selectedBook.getTytul());
        int nowaLiczbaEgzemplarzy = aktualnaLiczbaEgzemplarzy + 1;

        aktualizujLiczbeEgzemplarzy(selectedBook.getTytul(), nowaLiczbaEgzemplarzy);
    }

    private int pobierzLiczbeEgzemplarzy(String selectedBook) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String countQuery = "SELECT ilosc_sztuk FROM Ksiazki WHERE tytul = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
            preparedStatement.setString(1, selectedBook);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("ilosc_sztuk");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void aktualizujLiczbeEgzemplarzy(String selectedBook, int nowaLiczbaEgzemplarzy) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String updateQuery = "UPDATE Ksiazki SET ilosc_sztuk = ? WHERE tytul = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, nowaLiczbaEgzemplarzy);
            preparedStatement.setString(2, selectedBook);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
