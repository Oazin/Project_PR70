package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import fr.pr70.project_pr70.back.User;
import fr.pr70.project_pr70.back.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class TaskCreationController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private Button createBtn;

    @FXML
    private Label invalidText;

    @FXML
    public void handleCreateTask(ActionEvent e)
    {
        if (nameField.getText().trim().isEmpty() ||
                descriptionArea.getText().trim().isEmpty() ||
                startDatePicker.getValue() == null ||
                deadlinePicker.getValue() == null ||
                priorityComboBox.getValue() == null)
        {
            invalidText.setText("All field need to be completed");
        }
        else
        {
            String name = nameField.getText();
            String description = descriptionArea.getText();
            Date startdDate = java.sql.Date.valueOf(startDatePicker.getValue());
            Date deadline = java.sql.Date.valueOf(deadlinePicker.getValue());
            Priority priority = Priority.valueOf(priorityComboBox.getValue());
            UserManager userManager = MainApplication.getUserManager();
            User user = userManager.getUser(MainApplication.getCurrentUsername());
            user.addTask(name, description, startdDate, deadline, priority);
            System.out.println(user.getTasks());
            MainApplication.setDashboard();
        }

    }


    public void handleCancel()
    {
        MainApplication.setDashboard();
    }
}
