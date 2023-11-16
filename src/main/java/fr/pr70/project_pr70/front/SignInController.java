package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import fr.pr70.project_pr70.back.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController
{
    @FXML
    private Label invalidText;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    protected void onSubmitClicked()
    {
        // Vider les champs
        clearField();

        UserManager userManager = MainApplication.getUserManager();
        if(userManager.alreadyExist(userTextField.getText()))
        {
            invalidText.setText("This username already exist");
            return;
        }
        if(!userManager.confirmPassword(passwordTextField.getText(), confirmPasswordTextField.getText()))
        {
            invalidText.setText("Password and Confirm Password are different");
            return;
        }
        User user = new User(userTextField.getText(), passwordTextField.getText());
        user.setConnected(true);
        if(userManager.isEmpty())
        {
            user.setAdmin(true);
        }
        MainApplication.setCurrentUsername(user.getUsername());
        userManager.addUser(user);
        MainApplication.setDashboard();
    }

    /*! @brief : charge la page de connexion
     */
    @FXML
    protected void handleLogin()
    {
        MainApplication.setLogin();
    }

    /*! @brief : vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
        userTextField.clear();
        passwordTextField.clear();
        confirmPasswordTextField.clear();
    }
}
