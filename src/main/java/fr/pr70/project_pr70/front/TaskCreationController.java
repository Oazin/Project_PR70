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
    private Label invalidText;

    @FXML
    public void handleCreateTask(ActionEvent e)
    {
        // Si l'un des champs est vide la creation ne peut pas s'effectuer donc un message previent l'utilisateur
        if (nameField.getText().trim().isEmpty() ||
                descriptionArea.getText().trim().isEmpty() ||
                startDatePicker.getValue() == null ||
                deadlinePicker.getValue() == null ||
                priorityComboBox.getValue() == null)
        {
            // Message de prevention
            invalidText.setText("All field need to be completed");
        }

        // Sinon la tâche est créée
        else
        {
            // Récuprération des champs
            String name = nameField.getText();
            String description = descriptionArea.getText();
            Date startdDate = java.sql.Date.valueOf(startDatePicker.getValue());
            Date deadline = java.sql.Date.valueOf(deadlinePicker.getValue());
            Priority priority = Priority.valueOf(priorityComboBox.getValue());

            // Récupération de la liste des utilisateurs
            UserManager userManager = MainApplication.getUserManager();

            // Récupération de l'utilisateur courant et donc de ses inforamations
            User user = userManager.getUser(MainApplication.getCurrentUsername());

            // Création de la task
            user.addTask(name, description, startdDate, deadline, priority);

            //Charge le dashboard
            MainApplication.setDashboard();
        }

    }

    /*! @brief : Action lier au bouton cancel sur l'affichage graffique
     *
     *  Behaviour : Permet a l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas créer de nouvelle tâches
     */
    @FXML
    public void handleCancel()
    {
        MainApplication.setDashboard();
    }

}
