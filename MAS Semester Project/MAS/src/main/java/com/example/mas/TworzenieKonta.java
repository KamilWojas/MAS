package com.example.mas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TworzenieKonta extends Stage {
    private Connection connection;
    private Runnable returnAction;

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    public TworzenieKonta() {
        setTitle("Rejestracja");

        Label accountTypeLabel = new Label("Typ konta:");
        ComboBox<String> accountTypeComboBox = new ComboBox<>();
        accountTypeComboBox.getItems().addAll("Czytelnik", "Bibliotekarz");

        Label usernameLabel = new Label("Nazwa użytkownika:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Hasło:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Zarejestruj");
        registerButton.setOnAction(e -> {
            String accountType = accountTypeComboBox.getValue();
            String username = usernameField.getText();
            String password = passwordField.getText();

            saveAccountToDatabase(accountType, username, password);

            showAlert(Alert.AlertType.INFORMATION, "Rejestracja", null, "Konto zostało zarejestrowane!");

            if (returnAction != null) {
                returnAction.run();
            }

            close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                accountTypeLabel, accountTypeComboBox,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                registerButton
        );

        setScene(new Scene(layout, 300, 200));
    }

    private void saveAccountToDatabase(String accountType, String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "INSERT INTO Uzytkownicy (nazwa_uzytkownika, haslo, account_type) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, accountType);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", null, "Błąd podczas zapisywania konta do bazy danych.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
