package com.example.mas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class RejestracjaILogowanie extends Application {

    private Connection connection;

    private Stage primaryStage;
    private VBox mainContainer;
    private Scene loginScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("System Rejestracji i Logowania");

        mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10));

        loginScene = createLoginScene();

        showLoginWindow();

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public void showLoginWindow() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("System Rejestracji i Logowania");
        primaryStage.show();
    }

    private Scene createLoginScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label usernameLabel = new Label("Nazwa użytkownika:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Hasło:");
        PasswordField passwordField = new PasswordField();

        Label emailLabel = new Label("Adres e-mail:");
        TextField emailField = new TextField();

        Button loginButton = new Button("Zaloguj");
        loginButton.setOnAction(e -> login(usernameField.getText(), passwordField.getText()));

        Button registerButton = new Button("Zarejestruj");
        registerButton.setOnAction(e -> {
            TworzenieKonta tworzenieKonta = new TworzenieKonta();
            tworzenieKonta.setReturnAction(() -> showLoginWindow());

            tworzenieKonta.show();
        });

        layout.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                loginButton,
                registerButton
        );

        return new Scene(layout, 300, 200);
    }

    private boolean checkLoginCredentials(String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

            String query = "SELECT * FROM Uzytkownicy WHERE nazwa_uzytkownika = ? AND haslo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean loginSuccessful = resultSet.next();

            connection.close();

            return loginSuccessful;
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Błąd", null, "Błąd podczas sprawdzania danych logowania w bazie danych.");
            return false;
        }
    }

    private void login(String username, String password) {
        boolean loginSuccessful = checkLoginCredentials(username, password);

        if (loginSuccessful) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

                String query = "SELECT account_type FROM Uzytkownicy WHERE nazwa_uzytkownika = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String accountType = resultSet.getString("account_type");

                    connection.close();

                    if (accountType.equals("Bibliotekarz")) {
                        OknoBibliotekarza oknoBibliotekarza = new OknoBibliotekarza();
                        oknoBibliotekarza.setReturnAction(() -> {
                            showLoginWindow();
                            oknoBibliotekarza.close();
                        });
                        oknoBibliotekarza.show();
                        primaryStage.hide();
                    } else if (accountType.equals("Czytelnik")) {
                        Czytelnik czytelnik = new Czytelnik();
                        czytelnik.setNazwa(username);  // Ustawienie nazwy czytelnika

                        OknoCzytelnika oknoCzytelnika = new OknoCzytelnika();
                        oknoCzytelnika.setCzytelnik(czytelnik);  // Przypisanie obiektu Czytelnik
                        oknoCzytelnika.showBorrowedBooksWindow();  // Wywołanie odpowiedniej metody
                        oknoCzytelnika.setReturnAction(() -> {
                            showLoginWindow();
                            oknoCzytelnika.close();
                        });
                        oknoCzytelnika.show();
                        primaryStage.hide();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Błąd", null, "Błąd podczas sprawdzania typu konta w bazie danych.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Logowanie", null, "Nieprawidłowe dane logowania!");
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
