package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import javafx.fxml.FXML;

public class DashboardController
{


    @FXML
    private void handleNewTask() {
        MainApplication.setTaskCreation();
    }

    @FXML
    private void handleNewCategory() {

    }

}
