package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class EditController implements Cancelable{

    @FXML
    protected TextField nameField;

    @FXML
    protected TextArea descriptionArea;

    @FXML
    protected DatePicker startDatePicker;

    @FXML
    protected DatePicker deadlinePicker;

    @FXML
    protected ComboBox<String> priorityComboBox;

    @FXML
    protected Label invalidText;

    @FXML
    protected Button saveTaskButton;

    /**
     *  Action lier au bouton Save sur l'affichage graphique
     *  @param _task ; Tâche à modifier
     */
    @FXML
    public void handleSaveTask(Task _task)
    {
        _task.setName(nameField.getText());
        _task.setDescription(descriptionArea.getText());
        Date startDate = Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        _task.setStartDate(startDate);
        Date deadline = Date.from(deadlinePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        _task.setDeadline(deadline);
        _task.setPriority(Priority.valueOf(priorityComboBox.getValue()));
        MainApplication.setDashboard();
    }

    /**
     *  Action lier au bouton Cancel sur l'affichage graphique
     *  @param actionEvent ; Action lier au bouton Cancel sur l'affichage graphique
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        MainApplication.setDashboard();
    }

    /**
     *  Permet de mettre à jour les informations des champs de l'affichage graphique
     *  @param _task ; Tâche à modifier
     */
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
        priorityComboBox.setValue(_task.getPriority().toString());
        invalidText.setTextFill(Color.RED);
        saveTaskButton.setOnAction(actionEvent -> { handleSaveTask(_task); });
    }


    /**
     *  Vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
        nameField.clear();
        descriptionArea.clear();
        startDatePicker.setValue(null);
        deadlinePicker.setValue(null);
    }
}
