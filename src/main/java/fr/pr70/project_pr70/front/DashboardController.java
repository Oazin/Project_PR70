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
        // clear taskList
        taskBox.getChildren().clear();

        // get Currrent User
        User user = MainApplication.getUserManager().getUser(MainApplication.getCurrentUsername());
        if(user == null) return;

        // update header
        HBox header = new HBox();
        header.setId("header");
        taskBox.getChildren().add(header);
        if(user.isAdmin())
        {
            Label assigned = new Label("assigned");
            header.getChildren().add(assigned);
        }
        Label name = new Label("name");
        Label status = new Label("status");
        Label priority = new Label("priority");
        Label deadline = new Label("deadline");
        header.getChildren().addAll(name, status, priority, deadline);

        //update task
        TaskManager taskManager = user.getTasks();
        ArrayList<Task> tasks = taskManager.getTasks();
        for(Task task: tasks)
        {
            Label taskName = new Label(task.getName());
            Label taskStatus = new Label();
            if(task.isCompleted())
            {
                taskStatus.setText("completed");
                taskStatus.setId("completed");
            }
            else
            {
                taskStatus.setText("to do");
                taskStatus.setId("todo");
            }
            Label taskPriority = new Label();
            if(task.getPriority() == Priority.HIGH)
                taskPriority.setText("HIGH");
            else if (task.getPriority() == Priority.MEDIUM)
                taskPriority.setText("MEDIUM");
            else
                taskPriority.setText("LOW");
            ProgressBar taskDeadline = new ProgressBar(task.getTimePercent());
            HBox hBox = new HBox(taskName, taskStatus, taskPriority, taskDeadline);
            hBox.setId("task");
            hBox.setAlignment(Pos.CENTER_LEFT);
            //hBox.setAlignment(Pos.CENTER);
            taskBox.getChildren().add(hBox);
        }
    }
}
