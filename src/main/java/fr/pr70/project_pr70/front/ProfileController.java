package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label userLabel;

    @FXML
    private TextField OldpasswordTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private  TextField confirmPasswordTextField;

    @FXML
    public void handleEdit(){

    }

    @FXML
    public void handleCancel()
    {
        MainApplication.setDashboard();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLabel = new Label("test");
    }
}
