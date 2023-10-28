package fr.pr70.project_pr70.front;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");

        // Créer des éléments de la page de login
        Label usernameLabel = new Label("Nom d'utilisateur:");
        Label passwordLabel = new Label("Mot de passe:");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Connexion");

        // Créer une mise en page pour les éléments de la page de login
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Gérer l'action du bouton de connexion
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Vérifier les informations d'authentification ici
            // Si les informations sont correctes, vous pouvez naviguer vers le tableau de bord
        });

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
