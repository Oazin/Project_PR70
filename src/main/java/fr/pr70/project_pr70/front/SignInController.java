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
    private Label invalidText;

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
        if (!userManager.alreadyExist(userTextField.getText())){
            User user = new User(userTextField.getText(), passwordTextField.getText());
            user.setConnected(true);
            if(userManager.isEmpty())
            {
                user.setAdmin(true);
            }
            MainApplication.setCurrentUsername(user.getUsername());
            userManager.addUser(user);
            MainApplication.setDashboard();
        } else {
            invalidText.setText("This username already exist");
        }

    }

    @FXML
    protected void handleLogin(){
        MainApplication.setLogin();
    }
}
