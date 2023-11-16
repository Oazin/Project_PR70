package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
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
    private ComboBox<Category> categoryComboBox;

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
                priorityComboBox.getValue() == null ||
                categoryComboBox.getValue() == null)
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
            Date startDate = Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date deadline = Date.from(deadlinePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Priority priority = Priority.valueOf(priorityComboBox.getValue());
            Category category = categoryComboBox.getValue();

            // Récupération de la liste des utilisateurs
            UserManager userManager = MainApplication.getUserManager();

            // Récupération de l'utilisateur courant et donc de ses inforamations
            User user = userManager.getUser(MainApplication.getCurrentUsername());

            // Création de la task
            user.addTask(name, description, startDate, deadline, priority, category);

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

    public void updateCategoryComboBox()
    {
        categoryComboBox.getItems().clear();
        ArrayList<Category> categories = MainApplication.getCategoryManager().getCategories();
        if(!categories.isEmpty())
        {
            categoryComboBox.setValue(categories.get(0));
        }
        categoryComboBox.getItems().addAll(categories);
    }
}
