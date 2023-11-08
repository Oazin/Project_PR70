package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Task;
import fr.pr70.project_pr70.back.TaskManager;
import fr.pr70.project_pr70.back.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DashboardController
{
    @FXML
    protected VBox taskBox;

    @FXML
    private void handleNewTask()
    {
        MainApplication.setTaskCreation();
    }

    @FXML
    private void handleNewCategory()
    {
        MainApplication.setCategoryCreation();
    }

    @FXML
    private void handleProfile()
    {
        MainApplication.setProfile();
    }

    @FXML
    private void handleLogout(){
        MainApplication.setCurrentUsername("");
        MainApplication.setLogin();
    }

    @FXML
    public void updateTaskList()
    {
        User user = MainApplication.getUserManager().getConnectedUser();
        if(user == null) return;
        TaskManager taskManager = user.getTasks();
        ArrayList<Task> tasks = taskManager.getTasks();
        for(Task task: tasks)
        {
            HBox hBox = new HBox();
            Label label = new Label();
            label.setText(task.getName());
            hBox.getChildren().add(label);
            taskBox.getChildren().add(hBox);
        }
    }
}
