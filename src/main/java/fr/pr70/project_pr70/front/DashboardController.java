package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.Priority;
import fr.pr70.project_pr70.back.Task;
import fr.pr70.project_pr70.back.TaskManager;
import fr.pr70.project_pr70.back.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
        // clear taskList
        taskBox.getChildren().clear();

        // get Currrent User
        User user = MainApplication.getUserManager().getUser(MainApplication.getCurrentUsername());
        if(user == null) return;

        TableView<Task> tableView = new TableView<Task>();
        tableView.setId("header");
        taskBox.getChildren().add(tableView);
        TableColumn<User, String> assignedCol;
        if(user.isAdmin())
        {
            assignedCol = new TableColumn<User, String>("assigned");
            tableView.getColumns().add(assignedCol);
            assignedCol.setCellValueFactory(new PropertyValueFactory<>("assigned"));
        }
        TableColumn<Task, String> nameCol = new TableColumn<Task, String>("name");
        TableColumn<Task, String> statusCol = new TableColumn<Task, String>("status");
        TableColumn<Task, String> priorityCol = new TableColumn<Task, String>("priority");
        TableColumn<Task, String> deadlineCol = new TableColumn<Task, String>("deadline");
        tableView.getColumns().addAll(nameCol, statusCol, priorityCol, deadlineCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        TaskManager taskManager = user.getTasks();
        ArrayList<Task> tasks = taskManager.getTasks();
        ObservableList<Task> list = FXCollections.observableArrayList(tasks);
        tableView.setItems(list);

        // update header
        /**
        HBox header = new HBox();
        header.setId("header");
        //taskBox.getChildren().add(header);
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
            Label taskAssigned = new Label("you");
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
            HBox hBox = new HBox(taskAssigned, taskName, taskStatus, taskPriority, taskDeadline);
            hBox.setId("task");
            hBox.setAlignment(Pos.CENTER_LEFT);
            //hBox.setAlignment(Pos.CENTER);
            taskBox.getChildren().add(hBox);
        }**/
    }
}
