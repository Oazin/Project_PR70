package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import fr.pr70.project_pr70.back.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignInController
{
    @FXML
    private Label userLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField userTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    protected void onSubmitClicked()
    {
        UserManager userManager = MainApplication.getUserManager();
        User user = new User(userTextField.getText(), passwordTextField.getText());
        userManager.createUser(userTextField.getText(), passwordTextField.getText(), confirmPasswordTextField.getText());
        if(userManager.isEmpty())
        {
            user.setAdmin(true);
        }
        MainApplication.setCurrentUsername(user.getUsername());
        userManager.addUser(user);
        MainApplication.setDashboard();
    }
}
