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
        if (userTextField.getText().trim().isEmpty() || passwordTextField.getText().trim().isEmpty() || confirmPasswordTextField.getText().trim().isEmpty()){
            invalidText.setText("All fields need to be completed");
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

        // Vider les champs
        clearField();

        MainApplication.setDashboard();
    }

    /*! @brief : Action lier au bouton Login qui charge la page pour se connenecter
     */
    @FXML
    protected void handleLogin()
    {
        // Vider les champs
        clearField();
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
