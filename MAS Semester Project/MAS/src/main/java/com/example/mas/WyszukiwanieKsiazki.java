package com.example.mas;

import com.example.mas.SzczegolyKsiazki;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class WyszukiwanieKsiazki extends Stage {
    private Runnable returnAction;

    private Czytelnik czytelnik;

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    public WyszukiwanieKsiazki() {
        setTitle("Wyszukiwanie Książki");

        Label titleLabel = new Label("Wyszukaj książkę:");

        ListView<String> searchResultsListView = new ListView<>();
        TextField searchTextField = new TextField();

        Button searchButton = new Button("Szukaj");
        searchButton.setOnAction(e -> searchBooks(searchTextField.getText(), searchResultsListView));

        Button backButton = new Button("Powrót");
        backButton.setOnAction(e -> {
            if (returnAction != null) {
                returnAction.run();
            }
            close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, searchTextField, searchButton, searchResultsListView, backButton);

        setScene(new Scene(layout, 300, 300));
    }

    private void searchBooks(String searchText, ListView<String> searchResultsListView) {
        searchResultsListView.getItems().clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "SELECT * FROM ksiazki WHERE tytul LIKE ? OR autor LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tytul = resultSet.getString("tytul");
                String autor = resultSet.getString("autor");
                searchResultsListView.getItems().add(tytul + " - " + autor);
            }
            searchResultsListView.setOnMouseClicked(event -> {
                String selectedItem = searchResultsListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    SzczegolyKsiazki szczegolyKsiazki = new SzczegolyKsiazki(selectedItem, true);
                    szczegolyKsiazki.show();
                }
            });

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Błąd podczas wyszukiwania książek w bazie danych.");
        }
    }
}
