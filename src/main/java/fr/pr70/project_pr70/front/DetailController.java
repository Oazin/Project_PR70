package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DetailController {

    @FXML
    protected VBox infoBox;

    @FXML
    public void handleExit()
    {
        MainApplication.setDashboard();
    }

    @FXML
    public void updateDetail(Task task)
    {
        infoBox.getChildren().clear();
        Label description = new Label(task.getDescription());
        infoBox.getChildren().add(description);
    }
}
