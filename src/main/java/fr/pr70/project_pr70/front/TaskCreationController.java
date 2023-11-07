package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
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
    public void handleCreateTask(ActionEvent e)
    {
        if (nameField.getText().trim().isEmpty() ||
                descriptionArea.getText().trim().isEmpty() ||
                startDatePicker.getValue() == null ||
                deadlinePicker.getValue() == null ||
                priorityComboBox.getValue() == null)
        {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you haven't typed something");
            fail.showAndWait();
        }
        else
        {
            String name = nameField.getText();
            String description = descriptionArea.getText();
            Date startdDate = java.sql.Date.valueOf(startDatePicker.getValue());
            Date deadline = java.sql.Date.valueOf(deadlinePicker.getValue());
            Priority priority = Priority.valueOf(priorityComboBox.getValue());
            Task task = new Task(name, description, startdDate, deadline, priority);
            MainApplication.getUserManager().getConnectedUser().getTasks().getTasks().add(task);
            System.out.println(task);
            MainApplication.setDashboard();
        }

    }


    public void handleCancel()
    {
        MainApplication.setDashboard();
    }
}
