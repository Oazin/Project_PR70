package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Category;
import fr.pr70.project_pr70.back.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AddAdminController {

    @FXML
    protected ComboBox<String> adminsComboBox;

    @FXML
    private Label invalidText;


    /*! @brief : Action lier au bouton cancel sur l'affichage graffique
     *
     *  Behaviour : Permet a l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas créer de nouvelle tâches
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        MainApplication.setDashboard();
    }

    @FXML
    public void handleAdd(ActionEvent actionEvent)
    {
        // Si l'un des champs est vide la creation ne peut pas s'effectuer donc un message previent l'utilisateur
        if (adminsComboBox.getValue() == null)
        {
            // Message de prevention
            invalidText.setText("Choose a user");
        } else {
            User newAdmin = MainApplication.getUserManager().getUser(adminsComboBox.getValue());
            newAdmin.setAdmin(true);
            MainApplication.setDashboard();
        }

    }

    /*! @brief : vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
    }


    /*! @brief : Permet de mettre à jour et afficher la liste des utilisateurs non admin
     */
    public void updateUserComboBox()
    {
        // Vider les champs
        clearField();

        adminsComboBox.getItems().clear();

        ArrayList<User> users = MainApplication.getUserManager().getUsers();

        for (User u : users){
            if (!u.isAdmin()){
                adminsComboBox.getItems().add(u.getUsername());
            }
        }

    }
}
