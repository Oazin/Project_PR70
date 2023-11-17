package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class DashboardController
{
    @FXML
    protected ToolBar toolBar;

    @FXML
    protected VBox taskTable;

    /* ---------------- Button' handles -----------------*/

    /*! @brief : Action lier au bouton New task sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger la page de creation de tache
     */
    @FXML
    private void handleNewTask()
    {
        MainApplication.setTaskCreation();
    }

    /*! @brief : Action lier au bouton New Category sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger la page de creation de categorie
     */
    @FXML
    private void handleNewCategory()
    {
        MainApplication.setCategoryCreation();
    }

    /*! @brief : Action lier au bouton Profile sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger sa page de profile
     */
    @FXML
    private void handleProfile()
    {
        MainApplication.setProfile();
    }

    /*! @brief : Action lier au bouton logout sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de se deconnecter et charge la page de login
     */
    @FXML
    private void handleLogout(){
        MainApplication.setCurrentUsername("");
        MainApplication.setLogin();
    }

    /*! @brief : Action lier au bouton task Report sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de mettre en évidance une tache qui doit être fait
     */
    private void handleTaskReport(Task _task)
    {
        _task.setReported(true);
        updateTaskTable();
    }

    /*! @brief : Action lier au bouton Edit sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger la page de modification de la tache associer
     */
    private void handleTaskEdit(Task _task)
    {
        MainApplication.setEdit(_task);
    }

    /*! @brief : Action lier au bouton Delete sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de supprimer la tache s'associer
     */
    private void handleTaskDelete(UserManager _userManager, Task _task, String _userName)
    {
        _userManager.getUser(_userName).removeTask(_task);
        updateTaskTable();
    }

    /*! @brief : Action lier au bouton Task Status sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de changer le status de la tache associer
     */
    private void handleTaskStatus(Task _task)
    {
        _task.setCompleted(!_task.isCompleted());
        if(_task.isCompleted())
        {
            _task.setReported(false);
        }
        updateTaskTable();
    }

    /*! @brief : Action lier au bouton Add Admin sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger la page d'ajout d'un administrateur
     */
    private void handleAddAdmin() {
        MainApplication.setAddAdmin();
    }

    /*! @brief : Action lier au bouton Remove Admin sur l'affichage graphique
     *
     *  Behaviour : Permet à l'utilisateur de charger la page de suppression des droits d'un administrateur
     */
    private void handleRemoveAdmin() {
        MainApplication.setRemoveAdmin();
    }




    /*! @brief : Methode de creation dynamique de la tool bar du dashboard
     *
     *  Behaviour : Défini l'ensemble des propriétés fxml, de la tool bar,
     *  en code permetant de faire évoluer dynamiquement les éléments de la page.
     *  Ce principe permet d'afficher uniquement certain élément au utilisateur
     *  et des éléments suplémentaires aux administrateurs permettant d'administrer l'application
     */
    @FXML
    public void createToolBar()
    {
        toolBar.getItems().clear();

        // Définir les marges pour la barre d'outils
        toolBar.setPadding(new Insets(20.0));

        // Créer les boutons et leurs actions associées

        // Boutton pour acceder à la page de creation d'une nouvelle taches
        Button newTaskButton = new Button("New task");
        newTaskButton.setOnAction(event -> handleNewTask());
        newTaskButton.setId("button");
        toolBar.getItems().add(newTaskButton);

        // Seul un administrateur peut créer des categories et ajouter des administrateur
        User currentUser = MainApplication.getUserManager().getUser(MainApplication.getCurrentUsername());
        if(currentUser.isAdmin())
        {
            // Boutton pour acceder à la page de creation de categorie
            Button newCategoryButton = new Button("New category");
            newCategoryButton.setOnAction(event -> handleNewCategory());
            newCategoryButton.setId("button");

            // Boutton pour acceder à la page d'ajoute d'un administrateur
            Button addAdminButton = new Button();
            addAdminButton.setOnAction(event -> handleAddAdmin());
            ImageView addAdminImage = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/admin-add-logo.png").toString()));
            addAdminImage.setFitHeight(20);
            addAdminImage.setPreserveRatio(true);
            addAdminButton.setGraphic(addAdminImage);
            addAdminButton.setId("button");

            // Boutton pour acceder à la page de suppression d"un administrateur
            Button removeAdminButton = new Button();
            removeAdminButton.setOnAction(event -> handleRemoveAdmin());
            ImageView removeAdminImage = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/remove-admin-logo.png").toString()));
            removeAdminImage.setFitHeight(20);
            removeAdminImage.setPreserveRatio(true);
            removeAdminButton.setGraphic(removeAdminImage);
            removeAdminButton.setId("button");


            toolBar.getItems().addAll(newCategoryButton, addAdminButton, removeAdminButton);
        }


        // Boutton pour acceder à la page profile
        Button profileButton = new Button();
        profileButton.setOnAction(event -> handleProfile());
        ImageView profileImage = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/profile-logo.png").toString()));
        profileImage.setFitHeight(20);
        profileImage.setPreserveRatio(true);
        profileButton.setGraphic(profileImage);
        profileButton.setId("button");

        // Boutton pour se deconnecter
        Button logoutButton = new Button();
        logoutButton.setOnAction(event -> handleLogout());
        ImageView logoutImage = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/log-out-logo.png").toString()));
        logoutImage.setFitHeight(20);
        logoutImage.setPreserveRatio(true);
        logoutButton.setGraphic(logoutImage);
        logoutButton.setId("button");

        toolBar.getItems().addAll(profileButton, logoutButton);
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
            taskCategory.setId("category");
            Color categoryColor = task.getCategory().getColor();
            taskCategory.setStyle("-fx-background-color: rgb("+categoryColor.getRed()*255+", "+categoryColor.getGreen()*255+", "+categoryColor.getBlue()*255+")");
            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);
            HBox hBox = new HBox(taskAssigned, taskName, taskStatus, taskPriority, taskDeadline, taskCategory, region1);
            if(currentUser.isAdmin())
            {
                ImageView reportLogo = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/alarm-logo.png").toString()));
                reportLogo.setFitHeight(20);
                reportLogo.setPreserveRatio(true);
                Button taskReport = new Button();
                taskReport.setId("button");
                taskReport.setGraphic(reportLogo);
                taskReport.setOnAction(actionEvent -> { handleTaskReport(task); });
                Region spacer1 = new Region();
                spacer1.setId("spacer");
                hBox.getChildren().addAll(taskReport, spacer1);
            }

            ImageView editLogo = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/edit-logo.png").toString()));
            editLogo.setFitHeight(20);
            editLogo.setPreserveRatio(true);
            Button taskEdit = new Button();
            taskEdit.setId("button");
            taskEdit.setGraphic(editLogo);
            taskEdit.setOnAction(actionEvent -> { handleTaskEdit(task); });
            Region spacer2 = new Region();
            spacer2.setId("spacer");

            ImageView trashLogo = new ImageView(new Image(getClass().getResource("/fr/pr70/project_pr70/icon/trash-logo.png").toString()));
            trashLogo.setFitHeight(20);
            trashLogo.setPreserveRatio(true);
            Button taskDelete = new Button();
            taskDelete.setId("button");
            taskDelete.setGraphic(trashLogo);
            taskDelete.setOnAction(actionEvent -> { handleTaskDelete(userManager, task, userName); });
            Region spacer3 = new Region();
            spacer3.setId("spacer");

            hBox.getChildren().addAll(taskEdit, spacer2, taskDelete, spacer3);
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
