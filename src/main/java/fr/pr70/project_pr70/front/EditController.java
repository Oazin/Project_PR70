package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class EditController {

    @FXML
    protected TextField nameField;

    @FXML
    protected TextArea descriptionArea;

    @FXML
    protected DatePicker startDatePicker;

    @FXML
    protected DatePicker deadlinePicker;

    @FXML
    protected ComboBox<Priority> priorityComboBox;

    @FXML
    protected Label invalidText;

    @FXML
    protected Button saveTaskButton;

    @FXML
    public void handleSaveTask(Task _task)
    {
        _task.setName(nameField.getText());
        _task.setDescription(descriptionArea.getText());
        Date startDate = Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        _task.setStartDate(startDate);
        Date deadline = Date.from(deadlinePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        _task.setDeadline(deadline);
        _task.setPriority(priorityComboBox.getValue());
        MainApplication.setDashboard();
    }

    @FXML
    public void handleCancel()
    {
        MainApplication.setDashboard();
    }

    @FXML
    public void updateEdit(Task _task)
    {
        // Vider les champs
        clearField();

        nameField.setText(_task.getName());
        descriptionArea.setText(_task.getDescription());
        LocalDate startDate = _task.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        startDatePicker.setValue(startDate);
        LocalDate deadline = _task.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        deadlinePicker.setValue(deadline);
        priorityComboBox.setValue(_task.getPriority());
        invalidText.setTextFill(Color.RED);
        saveTaskButton.setOnAction(actionEvent -> { handleSaveTask(_task); });
    }


    /*! @brief : vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
        nameField.clear();
        descriptionArea.clear();
        startDatePicker.setValue(null);
        deadlinePicker.setValue(null);
    }
}
