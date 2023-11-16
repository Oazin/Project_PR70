package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Category;
import fr.pr70.project_pr70.back.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AddAdminController {

    @FXML
    protected ComboBox<String> adminsComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        MainApplication.setDashboard();
    }

    @FXML
    protected VBox adminTable;

    public void updateCategoryComboBox()
    {
        adminTable.getChildren().clear();

        adminsComboBox.getItems().clear();

        ArrayList<User> admin = new ArrayList<>();

        for (User u : MainApplication.getUserManager().getUsers()){
            if (u.isAdmin()){
                admin.add(u);
            }
        }

        for (User u : admin){
            adminsComboBox.getItems().add(u.getUsername());
        }

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(this::handleCancel);
        cancelButton.setId("button");

        adminTable.getChildren().addAll(adminsComboBox, cancelButton);

    }
}
