package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class AddAdminController implements Cancelable {

    @FXML
    protected ComboBox<String> userComboBox;

    @FXML
    private Label invalidText;


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
     *  Ajoute les droits administrateurs à un utilisateur
     *  @behaviour : Permet à un administrateur d'ajouter les droits
     *  administrateurs à un autre utilisateur qui n'était pas administrateur
     */
    @FXML
    public void handleAdd()
    {
        // Si l'un des champs est vide la creation ne peut pas s'effectuer donc un message previent l'utilisateur
        if (userComboBox.getValue() == null)
        {
            // Message de prevention
            invalidText.setText("Choose a user");
        } else {
            User newAdmin = MainApplication.getUserManager().getUser(userComboBox.getValue());
            newAdmin.setAdmin(true);
            MainApplication.setDashboard();
        }

    }

    /**
     *  Vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
    }


    /**
     *  Permet de mettre à jour et afficher la liste des utilisateurs non admin
     */
    public void updateUserComboBox()
    {
        // Vider les champs
        clearField();

        userComboBox.getItems().clear();

        ArrayList<User> users = MainApplication.getUserManager().getUsers();

        // Remplis la drop down liste avec le nom des utilisteurs qui ne sont pas administrateur
        for (User u : users){
            if (!u.isAdmin()){
                userComboBox.getItems().add(u.getUsername());
            }
        }

    }
}
