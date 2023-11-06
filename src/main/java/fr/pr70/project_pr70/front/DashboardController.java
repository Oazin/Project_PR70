package fr.pr70.project_pr70.front;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DashboardController
{
    @FXML
    private VBox mainVBox;

    @FXML
    private SplitPane mainPane;

    @FXML
    private HBox navigationBar;

    @FXML
    private VBox taskVBox;

    @FXML
    private Button button;

    @FXML
    public void initialize()
    {
        navigationBar.setMaxHeight(80);
        mainPane.setStyle("-fx-box-border: transparent;\n");

    }
}
