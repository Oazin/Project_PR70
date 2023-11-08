package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import fr.pr70.project_pr70.back.TaskManager;
import fr.pr70.project_pr70.back.User;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DashboardController
{
    @FXML
    protected VBox taskBox;

    @FXML
    protected Label assignedLabel;

    @FXML
    private void handleNewTask()
    {
        MainApplication.setTaskCreation();
    }

    @FXML
    private void handleNewCategory()
    {

    }

    @FXML
    public void updateTaskList()
    {
        User user = MainApplication.getUserManager().getConnectedUser();
        if(user == null) return;
        if(user.isConnected())
            assignedLabel.setVisible(false);
        TaskManager taskManager = user.getTasks();
        ArrayList<Task> tasks = taskManager.getTasks();
        for(Task task: tasks)
        {
            Label name = new Label();
            name.setText(task.getName());
            Label priority = new Label();
            if(task.getPriority() == Priority.HIGH)
                priority.setText("HIGH");
            else if (task.getPriority() == Priority.MEDIUM)
                priority.setText("MEDIUM");
            else
                priority.setText("LOW");
            ProgressBar deadline = new ProgressBar();
            deadline.setProgress(task.getTimePercent());
            System.out.println(task.getTimePercent());
            HBox hBox = new HBox(name, priority, deadline);
            hBox.setId("task");
            hBox.setAlignment(Pos.CENTER_LEFT);
            //hBox.setAlignment(Pos.CENTER);
            taskBox.getChildren().add(hBox);
        }
    }
}
