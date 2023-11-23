package fr.pr70.project_pr70.front;


import fr.pr70.project_pr70.MainApplication;
import fr.pr70.project_pr70.back.*;
import javafx.event.ActionEvent;
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
    private void handleTaskDelete(Task _task, String _userName)
    {
        MainApplication.getUserManager().getUser(_userName).removeTask(_task);
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

    /*!
    * @brief : ajoute un handle pour l'ouverture de la page de detail de la tache
    */
    private void setHandleDetail(HBox hBox, Task task)
    {
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
        setDashboardButton(event -> handleNewTask(), newTaskButton);

        toolBar.getItems().add(newTaskButton);

        // Seul un administrateur peut créer des categories et ajouter des administrateur
        User currentUser = MainApplication.getCurrentUser();
        if(currentUser.isAdmin())
        {
            // Boutton pour acceder à la page de creation de categorie
            Button newCategoryButton = new Button("New category");
            setDashboardButton(event -> handleNewCategory(), newCategoryButton);

            // Boutton pour acceder à la page d'ajoute d'un administrateur
            Button addAdminButton = new Button();
            setDashboardButton(event -> handleAddAdmin(), "/fr/pr70/project_pr70/icon/admin-add-logo.png", addAdminButton);

            // Boutton pour acceder à la page de suppression d"un administrateur
            Button removeAdminButton = new Button();
            setDashboardButton(event -> handleRemoveAdmin(), "/fr/pr70/project_pr70/icon/remove-admin-logo.png", removeAdminButton);

            toolBar.getItems().addAll(newCategoryButton, addAdminButton, removeAdminButton);
        }


        // Boutton pour acceder à la page profile
        Button profileButton = new Button();
        setDashboardButton(event -> handleProfile(), "/fr/pr70/project_pr70/icon/profile-logo.png", profileButton);

        // Boutton pour se deconnecter
        Button logoutButton = new Button();
        setDashboardButton(event -> handleLogout(),"/fr/pr70/project_pr70/icon/log-out-logo.png", logoutButton );

        toolBar.getItems().addAll(profileButton, logoutButton);
    }

    /*! @brief : Defini l'ensemble des propriétés d'un boutton
     *  @param _handler ; le event handler associer au bouton
     *  @param _logoPath ; le chemin du logo associer au bouton
     *  @param _button ; le bouton que l'on souhaite paramètré
     *
     *  @behaviour :
     *  Defini l'action et le logo assossiés au bouton
     */
    private void setDashboardButton(EventHandler<ActionEvent> _handler, String _logoPath, Button _button)
    {
        _button.setOnAction(_handler);
        ImageView buttonImage = new ImageView(new Image(getClass().getResource(_logoPath).toString()));
        buttonImage.setFitHeight(20);
        buttonImage.setPreserveRatio(true);
        _button.setGraphic(buttonImage);
        _button.setId("button");
    }

    /*! @brief : Defini l'ensemble des propriétés d'un boutton
     *  @param _handler ; le event handler associer au bouton
     *  @param _button ; le bouton que l'on souhaite paramètré
     *
     *  @behaviour :
     *  Defini l'action assossiés au bouton
     */
    private void setDashboardButton(EventHandler<ActionEvent> _handler, Button _button)
    {
        _button.setOnAction(_handler);
        _button.setId("button");
    }

    /*! @brief : Methode de creation dynamique du header de la table des taches
     */
    private void updateHeader()
    {
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
    }

    /*! @brief : Methode de creation dynamique d'une tache
     *  @param _currentUser ; utilisateur courant
     *  @param _userName ; nom de l'utilisateur associé à la tache
     *  @param _task ; tache que l'on souhaite afficher
     *  @return : HBox ; le HBox contenant l'ensemble des informations de la tache
     */
    private HBox updateTask(User _currentUser, String _userName, Task _task)
    {
        // assigned label
        Label taskAssigned = new Label(_userName);
        if(_userName.equals(_currentUser.getUsername()))
            taskAssigned.setText("you");

        // name label
        Label taskName = new Label(_task.getName());

        // status label
        Label taskStatus = new Label();
        taskStatus.setText(_task.isCompleted()?"completed":"to do");
        taskStatus.setId(_task.isCompleted()?"completed":"todo");
        taskStatus.setOnMouseClicked(eventHandle -> { handleTaskStatus(_task); });

        // priority label
        Label taskPriority = new Label(_task.getPriority().toString());

        // deadline progress bar
        ProgressBar taskDeadline = new ProgressBar(_task.getTimePercent());

        // category label
        Label taskCategory = new Label(_task.getCategory().toString());
        taskCategory.setId("category");
        Color categoryColor = _task.getCategory().getColor();
        taskCategory.setStyle("-fx-background-color: rgb("+categoryColor.getRed()*255+", "+categoryColor.getGreen()*255+", "+categoryColor.getBlue()*255+")");
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        HBox hBox = new HBox(taskAssigned, taskName, taskStatus, taskPriority, taskDeadline, taskCategory, region1);

        // report button
        if(_currentUser.isAdmin())
        {
            Button taskReport = new Button();
            setDashboardButton(actionEvent -> { handleTaskReport(_task); }, "/fr/pr70/project_pr70/icon/alarm-logo.png", taskReport);
            Region spacer1 = new Region();
            spacer1.setId("spacer");

            hBox.getChildren().addAll(taskReport, spacer1);
        }

        // task edit button
        Button taskEdit = new Button();
        setDashboardButton(actionEvent -> { handleTaskEdit(_task); }, "/fr/pr70/project_pr70/icon/edit-logo.png", taskEdit);
        Region spacer2 = new Region();
        spacer2.setId("spacer");

        // task delete button
        Button taskDelete = new Button();
        setDashboardButton(actionEvent -> { handleTaskDelete(_task, _userName); }, "/fr/pr70/project_pr70/icon/trash-logo.png", taskDelete);
        Region spacer3 = new Region();
        spacer3.setId("spacer");

        hBox.getChildren().addAll(taskEdit, spacer2, taskDelete, spacer3);
        hBox.setId("task");
        if(_task.isReported())
            hBox.setStyle("-fx-background-color: red");
        setHandleDetail(hBox, _task);
        return hBox;
    }

    /*!
     * @brief : Methode de creation dynamique de la table des taches du dashboard
     */
    @FXML
    public void updateTaskTable()
    {
        // clear taskList
        taskTable.getChildren().clear();

        User currentUser = MainApplication.getCurrentUser();
        if(currentUser == null) return;

        updateHeader();

        //update tasks

        // lister les taches de tous les utilisateurs associés
        // recupérer les utilisateurs autorisés
        ArrayList<User> users = MainApplication.getUserManager().getAllowedUsers(MainApplication.getCurrentUser());
        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        for(User user : users)
        {
            TaskManager taskManager = user.getTasks();
            for(Task task : taskManager.getTasks())
            {
                userNames.add(user.getUsername());
            }
            tasks.addAll(taskManager.getTasks());
        }

        for(int i = 0; i < tasks.size(); i++)
        {
            String userName = userNames.get(i);
            Task task = tasks.get(i);
            HBox hBox = updateTask(currentUser, userName, task);
            taskTable.getChildren().add(hBox);
        }
    }
}
