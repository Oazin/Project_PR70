package fr.pr70.project_pr70.front;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class DashboardController
{
    @FXML
    private VBox mainVBox;

    public DashboardController()
    {
        String cssLayout = "-fx-border-color: red;\n-fx-border-insets: 5;\n-fx-border-width: 3;\n-fx-border-style: dashed;\n";
        this.mainVBox.setStyle(cssLayout);
    }

}
