package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Date;

public class CategoryCreationContoller {
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private Label invalidText;

    @FXML
    public void handleCreateCategory(ActionEvent e)
    {
        if (nameField.getText().trim().isEmpty() ||
                colorComboBox.getValue() == null)
        {
            invalidText.setText("All field need to be completed");
        }
        else
        {
            String name = nameField.getText();
            Color color = Color.valueOf(colorComboBox.getValue());
            CategoryManager categoryManager = MainApplication.getCategoryManager();
            Category category = new Category(name, color);
            categoryManager.addCategories(category);
            MainApplication.setDashboard();
        }

    }
    @FXML
    public void handleCancel()
    {
        MainApplication.setDashboard();
    }
}
