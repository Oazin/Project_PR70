package fr.pr70.project_pr70.front;

import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DetailController {

    @FXML
    protected VBox infoBox;

    /**
     *  Action lier au bouton Exit sur l'affichage graphique
     *  @behaviour : Permet à l'utilisateur de retourner sur le dashboard
     *  lorsqu'il decide de ne pas créer de nouvelle tâches
     */
    @FXML
    public void handleExit()
    {
        MainApplication.setDashboard();
    }

    /**
     * Met à jour les informations de la tâche affichée sur l'affichage graphique
     * @param _task; Tâche dont les informations doivent être affichées
     */
    @FXML
    public void updateDetail(Task _task)
    {
        infoBox.getChildren().clear();
        Label name = new Label(_task.getName());
        name.setStyle("-fx-font-weight: bold");
        Label description = new Label(_task.getDescription());
        Label priority = new Label(_task.getPriority().toString());
        Label deadLine = new Label(_task.getDeadline().toString());
        infoBox.getChildren().addAll(name, description, priority, deadLine);
    }
}
