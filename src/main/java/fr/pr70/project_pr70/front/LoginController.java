package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController
{
    @FXML
    private Label invalidText;

    @FXML
    private Label userLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField userTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    protected void onSubmitClicked()
    {
        UserManager userManager = MainApplication.getUserManager();
        boolean connected = userManager.connectUser(userTextField.getText(), passwordTextField.getText());
        if(connected)
        {
            MainApplication.setCurrentUsername(userTextField.getText());
            MainApplication.setDashboard();
        } else {
            invalidText.setText("Wrong username or password");
        }
    }

    @FXML
    protected void handleSigin(){
        MainApplication.setSignIn();
    }
}
