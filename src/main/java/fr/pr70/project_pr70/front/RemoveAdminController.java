package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class RemoveAdminController implements Cancelable{

    @FXML
    protected ComboBox<String> adminsComboBox;

    @FXML
    private Label invalidText;


    /**
     *  Action lier au bouton cancel sur l'affichage graphique
     *  @param actionEvent ; Action lier au bouton cancel sur l'affichage graphique
     *  @behaviour : Permet a l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas créer de nouvelle tâches
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        MainApplication.setDashboard();
    }


    /**
     *  Action lier au bouton remove sur l'affichage graphique
     *  @behaviour : Permet à un administrateur de retirer les droits
     *  administrateurs à un autre utilisateur qui était administrateur
     */
    @FXML
    public void handleRemove()
    {
        // Si l'un des champs est vide la creation ne peut pas s'effectuer donc un message previent l'utilisateur
        if (adminsComboBox.getValue() == null)
        {
            // Message de prevention
            invalidText.setText("Choose a user");
        } else {
            User removedAdmin = MainApplication.getUserManager().getUser(adminsComboBox.getValue());
            removedAdmin.setAdmin(false);
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
     *  Permet de mettre à jour et afficher la liste des administrateurs
     */
    public void updateAdminComboBox()
    {
        // Vider les champs
        clearField();

        adminsComboBox.getItems().clear();

        ArrayList<User> users = MainApplication.getUserManager().getUsers();

        for (User u : users){
            // Remplis la drop down liste avec le nom des administrateurs qui ne sont pas l'admin connecter
            if (u.isAdmin() && !u.getUsername().equals(MainApplication.getCurrentUser().getUsername())){
                adminsComboBox.getItems().add(u.getUsername());
            }
        }

    }
}
