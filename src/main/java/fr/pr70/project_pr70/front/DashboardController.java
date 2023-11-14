package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        // clear taskList
        taskBox.getChildren().clear();

        // get Currrent User
        UserManager userManager = MainApplication.getUserManager();

        User currentUser = userManager.getUser(MainApplication.getCurrentUsername());
        if(currentUser == null) return;

        List<User> users;
        if(currentUser.isAdmin())
        {
            users = userManager.getUsers();
        }
        else
        {
            users = new ArrayList<>();
            users.add(currentUser);
        }
        // update header
        HBox header = new HBox();
        header.setId("header");
        taskBox.getChildren().add(header);
        Label assigned = new Label("assigned");
        Label name = new Label("name");
        Label status = new Label("status");
        Label priority = new Label("priority");
        Label deadline = new Label("deadline");
        header.getChildren().addAll(assigned, name, status, priority, deadline);

        //update task
        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        for(User user : users)
        {
            TaskManager taskManager = user.getTasks();
            for(int i = 0; i < taskManager.getTasks().size(); i++)
            {
                if(user.getUsername().equals(currentUser.getUsername()))
                {
                    userNames.add("you");
                }
                else
                {
                    userNames.add(user.getUsername());
                }
            }
            tasks.addAll(taskManager.getTasks());
        }

        for(int i = 0; i < tasks.size(); i++)
        {
            String userName;
            if(userNames.get(i).equals("you"))
            {
                userName = currentUser.getUsername();
            }
            else
            {
                userName = userNames.get(i);
            }
            Task task = tasks.get(i);
            Label taskAssigned = new Label(userNames.get(i));
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
            Region region = new Region();
            HBox.setHgrow(region, javafx.scene.layout.Priority.ALWAYS);
            HBox hBox = new HBox(taskAssigned, taskName, taskStatus, taskPriority, taskDeadline, region);
            if(currentUser.isAdmin())
            {
                Button taskReport = new Button("Report");
                taskReport.setOnAction(actionEvent -> {
                    task.setReported(true);
                    updateTaskList();
                });
                hBox.getChildren().add(taskReport);
            }
            Button taskEdit = new Button("Edit");
            taskEdit.setOnAction(actionEvent -> {
                System.out.println("Edit");
            });
            Button taskDelete = new Button("Delete");
            taskDelete.setOnAction(actionEvent -> {
                userManager.getUser(userName).removeTask(task);
                updateTaskList();
            });
            hBox.getChildren().addAll(taskEdit, taskDelete);
            hBox.setId("task");
            if(task.isReported())
            {
                hBox.setStyle("-fx-background-color: red");
            }
            //hBox.setAlignment(Pos.CENTER_LEFT);
            taskBox.getChildren().add(hBox);
        }
    }
}
