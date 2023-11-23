package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CategoryCreationContoller implements Cancelable{
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private Label invalidText;


    /**
     *  Action lier au bouton New category sur l'affichage graphique
     *  @behaviour : Permet à l'utilisateur de créer une nouvel categorie
     */
    @FXML
    public void handleCreateCategory()
    {
        // Si le champs nom ou le champs color n'est pas remplis la creation ne peut pas s'effectuer donc un message previent l'utilisateur
        if (nameField.getText().trim().isEmpty() ||
                colorComboBox.getValue() == null)
        {
            // Message de prevention
            invalidText.setText("All field need to be completed");
        }

        // Sinon la categorie est créée
        else
        {
            // Récuprération des champs
            String name = nameField.getText();
            Color color = Color.valueOf(colorComboBox.getValue());

            // Récupération du category manager
            CategoryManager categoryManager = MainApplication.getCategoryManager();

            // Créer la categorie avec les informations rentré par l'utilisateur
            Category category = new Category(name, color);

            // Ajoute la categorie au category manager
            categoryManager.addCategories(category);

            // Vider les champs
            clearField();

            // Charge le dashboard
            MainApplication.setDashboard();
        }

    }

    /**
     *  Action lier au bouton cancel sur l'affichage graffique
     *  @param actionEvent ; Action lier au bouton cancel sur l'affichage graphique
     *  @behaviour : Permet a l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas créer une categorie
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent)
    {
        // Vider les champs
        clearField();
        MainApplication.setDashboard();
    }


    /**
     *  Vide les champs remplissable et les messages de prévension
     */
    private void clearField(){
        invalidText.setText("");
        nameField.clear();
    }
}
