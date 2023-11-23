package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController
{
    @FXML
    private Label invalidText;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    /**
     *  Action lier au bouton submit sur l'affichage graphique
     */
    @FXML
    protected void handleSubmit()
    {
        UserManager userManager = MainApplication.getUserManager();
        boolean connected = userManager.connectUser(userTextField.getText(), passwordTextField.getText());
        if(connected)
        {
            MainApplication.setCurrentUsername(userTextField.getText());
            // Vider les champs
            clearField();
            MainApplication.setDashboard();
        } else {
            invalidText.setText("Wrong username or password");
        }
    }

    /**
     * Action lier au bouton Signin permetant de charger la page d'inscription
     */
    @FXML
    protected void handleSigin(){
        // Vider les champs
        clearField();
        MainApplication.setSignIn();
    }

    /**
     *  Vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
        userTextField.clear();
        passwordTextField.clear();
    }
}
