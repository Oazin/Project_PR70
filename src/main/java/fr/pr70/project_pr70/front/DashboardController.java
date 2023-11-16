package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class DashboardController
{
    @FXML
    protected VBox taskTable;

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

    private void handleTaskReport(Task _task)
    {
        _task.setReported(true);
        updateTaskTable();
    }

    private void handleTaskEdit(Task _task)
    {
        MainApplication.setEdit(_task);
    }

    private void handleTaskDelete(UserManager _userManager, Task _task, String _userName)
    {
        _userManager.getUser(_userName).removeTask(_task);
        updateTaskTable();
    }

    private void handleTaskStatus(Task _task)
    {
        _task.setCompleted(!_task.isCompleted());
        updateTaskTable();
    }

    @FXML
    public void updateTaskTable()
    {
        // clear taskList
        taskTable.getChildren().clear();

        // get Currrent User
        UserManager userManager = MainApplication.getUserManager();

        User currentUser = userManager.getUser(MainApplication.getCurrentUsername());
        if(currentUser == null) return;

        ArrayList<User> users;
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
        taskTable.getChildren().add(header);
        Label assigned = new Label("assigned");
        Label name = new Label("name");
        Label status = new Label("status");
        status.setId("bigLabel");
        Label priority = new Label("priority");
        Label deadline = new Label("deadline");
        Label category = new Label("category");
        header.getChildren().addAll(assigned, name, status, priority, deadline, category);

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
            taskStatus.setText(task.isCompleted()?"completed":"to do");
            taskStatus.setId(task.isCompleted()?"completed":"todo");
            taskStatus.setOnMouseClicked(eventHandle -> { handleTaskStatus(task); });
            Label taskPriority = new Label();
            taskPriority.setText(task.getPriority().toString());
            ProgressBar taskDeadline = new ProgressBar(task.getTimePercent());
            Label taskCategory = new Label(task.getCategory().toString());
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            HBox hBox = new HBox(taskAssigned, taskName, taskStatus, taskPriority, taskDeadline, taskCategory, region);
            if(currentUser.isAdmin())
            {
                Button taskReport = new Button("Report");
                taskReport.setOnAction(actionEvent -> { handleTaskReport(task); });
                hBox.getChildren().add(taskReport);
            }
            Button taskEdit = new Button("Edit");
            taskEdit.setOnAction(actionEvent -> { handleTaskEdit(task); });
            Button taskDelete = new Button("Delete");
            taskDelete.setOnAction(actionEvent -> { handleTaskDelete(userManager, task, userName); });
            hBox.getChildren().addAll(taskEdit, taskDelete);
            hBox.setId("task");
            if(task.isReported())
            {
                hBox.setStyle("-fx-background-color: red");
            }
            hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                        if(mouseEvent.getClickCount() == 2){
                            MainApplication.setDetail(task);
                        }
                    }
                }
            });
            //hBox.setAlignment(Pos.CENTER_LEFT);
            taskTable.getChildren().add(hBox);
        }
    }
}
