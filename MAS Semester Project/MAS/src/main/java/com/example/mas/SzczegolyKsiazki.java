    package com.example.mas;

    import javafx.geometry.Insets;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.VBox;
    import javafx.stage.Stage;

    import java.sql.*;
    import java.text.DecimalFormat;
    import java.text.DecimalFormatSymbols;
    import java.time.LocalDate;
    import java.util.Locale;

    public class SzczegolyKsiazki extends Stage {
        private String selectedBook;
        private boolean isBookAvailable;
        private int liczbaWypozyczen;
        private int liczbaEgzemplarzy;
        private int limitWypozyczen;

        public SzczegolyKsiazki(String selectedBook, boolean isBookAvailable) {
            this.selectedBook = selectedBook;
            this.isBookAvailable = isBookAvailable;
            this.liczbaWypozyczen = pobierzLiczbeWypozyczen(selectedBook);
            this.liczbaEgzemplarzy = pobierzLiczbeEgzemplarzy(selectedBook);
            this.limitWypozyczen = 3;

            setTitle("Szczegóły książki");

            Label bookLabel = new Label("Wybrana książka: " + selectedBook);

            Label availabilityLabel = new Label();
            if (isBookAvailable) {
                availabilityLabel.setText("Dostępna w bibliotece");
            } else {
                availabilityLabel.setText("Wypożyczona");
            }

            Label copiesLabel = new Label("Liczba dostępnych egzemplarzy: " + liczbaEgzemplarzy);

            Button checkAvailabilityButton = new Button("Sprawdź dostępność");
            checkAvailabilityButton.setOnAction(e -> {
                if (isBookAvailable) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dostępność książki");
                    alert.setHeaderText(null);
                    alert.setContentText("Książka " + selectedBook + " jest dostępna do wypożyczenia.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dostępność książki");
                    alert.setHeaderText(null);
                    alert.setContentText("Książka " + selectedBook + " jest obecnie wypożyczona.");
                    alert.showAndWait();
                }
            });

            Button borrowButton = new Button("Wypożycz");
            borrowButton.setOnAction(e -> {
                if (isBookAvailable) {
                    if (czySpelniaWarunkiWypozyczenia()) {
                        wypozyczKsiazke(selectedBook);
                        wyswietlKomunikatOWypozyczeniu(selectedBook);

                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

                            String selectIdQuery = "SELECT id FROM Ksiazki WHERE tytul LIKE ?";
                            PreparedStatement selectIdStatement = connection.prepareStatement(selectIdQuery);
                            selectIdStatement.setString(1, selectedBook);
                            ResultSet resultSet = selectIdStatement.executeQuery();
                            int bookId = 0;
                            if (resultSet.next()) {
                                bookId = resultSet.getInt("id");
                            }

                            // Aktualizacja flagi wypożyczenia dla wybranej książki
                            String updateQuery = "UPDATE Ksiazki SET kategoria_id = 1 WHERE id = ?";
                            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                            updateStatement.setInt(1, bookId);
                            updateStatement.executeUpdate();

                            connection.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warunki wypożyczenia");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie spełniasz warunków wypożyczenia książki. Sprawdź limit wypożyczeń, ważność karty oraz zaległości w zwrotach.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Dostępność książki");
                    alert.setHeaderText(null);
                    alert.setContentText("Książka " + selectedBook + " jest obecnie wypożyczona.");
                    alert.showAndWait();
                }
            });


            Button backButton = new Button("Powrót");
            backButton.setOnAction(e -> {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            });

            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            layout.getChildren().addAll(bookLabel, availabilityLabel, copiesLabel, checkAvailabilityButton, borrowButton, backButton);

            Scene scene = new Scene(layout);
            setScene(scene);
        }

        private int pobierzLiczbeWypozyczen(String selectedBook) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

                String countQuery = "SELECT COUNT(*) FROM Wypozyczenie WHERE tytul = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
                preparedStatement.setString(1, selectedBook);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }

        private int pobierzLiczbeEgzemplarzy(String selectedBook) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

                String countQuery = "SELECT liczba_egzemplarzy FROM Ksiazki WHERE tytul = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
                preparedStatement.setString(1, selectedBook);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("liczba_egzemplarzy");
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }

        private boolean czySpelniaWarunkiWypozyczenia() {

            if (limitWypozyczen <= 0) {
                System.out.println("Przekroczono limit wypożyczeń.");
                return false;
            }

            return true;
        }

        private void wypozyczKsiazke(String selectedBook) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "root", "Barca123456!");

                double cena = generujLosowaCene();
                String insertQuery = "INSERT INTO ksiazki (tytul, autor, rok_wydania, cena) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, selectedBook);
                preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now().plusDays(30)));
                preparedStatement.setDouble(4, cena);
                preparedStatement.executeUpdate();

                System.out.println("Wypożyczono książkę: " + selectedBook);
                System.out.println("Termin zwrotu: " + LocalDate.now().plusDays(30));
                System.out.println("Opłaty: " + cena + " zł");

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Błąd podczas wypożyczania książki: " + selectedBook);
            }
        }

        private void wyswietlKomunikatOWypozyczeniu(String selectedBook) {
            double cena = generujLosowaCene();
            String message = "Wypożyczono książkę: " + selectedBook + "\nTermin zwrotu: " + LocalDate.now().plusDays(7) + "\nOpłaty: " + cena + " zł";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wypożyczenie książki");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private double generujLosowaCene() {
            double minCena = 1.0;
            double maxCena = 100.0;
            double cena = minCena + Math.random() * (maxCena - minCena);

            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
            decimalFormatSymbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("#.00", decimalFormatSymbols);
            String formattedCena = decimalFormat.format(cena);
            return Double.parseDouble(formattedCena);
        }
    }



