package com.example.mas;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OknoBibliotekarza extends Stage {
    private Runnable returnAction;

    public void setReturnAction(Runnable returnAction) {
        this.returnAction = returnAction;
    }

    public OknoBibliotekarza() {
        setTitle("Okno Bibliotekarza");

        Label label = new Label("Witaj, Bibliotekarzu!");
        Button logoutButton = new Button("Wyloguj");
        logoutButton.setOnAction(e -> {
            if (returnAction != null) {
                returnAction.run();
            }
            close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label, logoutButton);

        setScene(new Scene(layout, 300, 200));
    }
}