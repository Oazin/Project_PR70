package fr.pr70.project_pr70;

import fr.pr70.project_pr70.back.CategoryManager;
import fr.pr70.project_pr70.back.Save;
import fr.pr70.project_pr70.back.Task;
import fr.pr70.project_pr70.back.UserManager;
import fr.pr70.project_pr70.front.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application
{
    private static UserManager userManager;

    private static String currentUsername;

    private static CategoryManager categoryManager;

    private static Save save;

    private static Stage stage;

    private static FXMLLoader loginView;

    private static FXMLLoader signInView;

    private static FXMLLoader dashboardView;

    private static FXMLLoader taskCreationView;

    private static FXMLLoader categoryCreationView;

    private static FXMLLoader profileView;

    private static FXMLLoader detailView;

    private static FXMLLoader editView;

    private static FXMLLoader addAdminView;

    private static FXMLLoader removeAdminView;

    private static Scene login;

    private static Scene signIn;
    private static Scene dashboard;

    private static Scene profile;

    private static Scene taskCreation;

    private static Scene categoryCreation;

    private static Scene detail;

    private static Scene edit;

    private static Scene addAdmin;

    private static Scene removeAdmin;

    /* ----------------- Getters ----------------- */

    /*! @brief : Revoie le nom de l'utilisateur courant
     */
    public static String getCurrentUsername() {
        return currentUsername;
    }

    /*! @brief : Revoie la liste des users enregistrer dans la base de l'application
     */
    public static UserManager getUserManager()
    {
        return userManager;
    }

    /*! @brief : Revoie la liste des categories
     */
    public static CategoryManager getCategoryManager() {
        return categoryManager;
    }

    /* ----------------- Setters ----------------- */

    /*! @brief : Defini l'utilisateur courant
     */
    public static void setCurrentUsername(String _currentUsername) {
        MainApplication.currentUsername = _currentUsername;
    }

    /* ----------------- Methods ----------------- */

    /*! @brief : Met à jour et affiche la scene du dashbord
     *
     *  Behaviour: Assigne le controller à la view
     *      Met à jour la information dynamique du dashboard
     *      Affiche la scene dans la fenêtre
     */
    public static void setDashboard()
    {
        DashboardController dashboardController = dashboardView.getController();
        dashboardController.createToolBar();
        dashboardController.updateTaskTable();
        stage.setScene(dashboard);
    }

    /*! @brief : Met à jour et affiche la scene du porfile de l'utilisateur
     *
     *  Behaviour: Assigne le controller à la view
     *      Met à jour la information dynamique du profile
     *      Affiche la scene dans la fenêtre
     */
    public static void setProfile()
    {
        ProfileController profileController = profileView.getController();
        profileController.updateProfile();
        stage.setScene(profile);
    }

    /*! @brief : Met à jour et affiche la scene de création des taches
     *
     *  Behaviour: Assigne le controller à la view
     *      Met à jour la information dynamique de la page
     *      Affiche la scene dans la fenêtre
     */
    public static void setTaskCreation()
    {
        TaskCreationController taskCreationController = taskCreationView.getController();
        taskCreationController.updateCategoryComboBox();
        stage.setScene(taskCreation);
    }

    /*! @brief : Affiche la scene de la creation de categorie
     */
    public static void setCategoryCreation() {
        stage.setScene(categoryCreation);
    }

    /*! @brief : Affiche la scene de la connexion
     */
    public static void setLogin(){
        stage.setScene(login);
    }

    /*! @brief : Affiche la scene d'inscription
     */
    public static void setSignIn(){
        stage.setScene(signIn);
    }

    /*! @brief : Met à jour et affiche la scene qui affiche les détails de la tache
     *
     *  Behaviour: Assigne le controller à la view
     *      Met à jour la information dynamique de la page
     *      Affiche la scene dans la fenêtre
     */
    public static void setDetail(Task _task)
    {
        DetailController detailController = detailView.getController();
        detailController.updateDetail(_task);
        stage.setScene(detail);
    }

    /*! @brief : Met à jour et affiche la scene de modification de tache
     *
     *  Behaviour: Assigne le controller à la view
     *      Met à jour la information dynamique de la page 
     *      Affiche la scene dans la fenêtre
     */
    public static void setEdit(Task _task)
    {
        EditController editController = editView.getController();
        editController.updateEdit(_task);
        stage.setScene(edit);
    }

    /*! @brief : Affiche la scene avec la liste des utilisateurs non administrateur
     */
    public static void setAddAdmin()
    {
        AddAdminController addAdminController = addAdminView.getController();
        addAdminController.updateUserComboBox();
        stage.setScene(addAdmin);
    }

    /*! @brief : Affiche la scene avec la liste des administrateurs
     */
    public static void setRemoveAdmin() {
        RemoveAdminController removeAdminController = removeAdminView.getController();
        removeAdminController.updateAdminComboBox();
        stage.setScene(removeAdmin);
    }


    /*! @brief : Assigne le style au differente scene du logiciel
     */
    private void setStyle()
    {
        //import style.css
        URL url = this.getClass().getResource("style.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        dashboard.getStylesheets().add(css);
        signIn.getStylesheets().add(css);
        login.getStylesheets().add(css);
        profile.getStylesheets().add(css);
        taskCreation.getStylesheets().add(css);
        categoryCreation.getStylesheets().add(css);
        addAdmin.getStylesheets().add(css);
        removeAdmin.getStylesheets().add(css);
    }


    @Override
    public void start(Stage _stage) throws IOException
    {
        stage = _stage;
        stage.setTitle("YO Task Manager");

        // Definition des view des page de l'application 
        signInView = new FXMLLoader(MainApplication.class.getResource("sign-in-view.fxml"));
        loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        dashboardView = new FXMLLoader(MainApplication.class.getResource("dashboard-view.fxml"));
        profileView = new FXMLLoader(MainApplication.class.getResource("profile-view.fxml"));
        taskCreationView = new FXMLLoader(MainApplication.class.getResource("task-creation-view.fxml"));
        categoryCreationView = new FXMLLoader(MainApplication.class.getResource("category-creation-view.fxml"));
        editView = new FXMLLoader(MainApplication.class.getResource("edit-view.fxml"));
        detailView = new FXMLLoader(MainApplication.class.getResource("detail-view.fxml"));
        addAdminView = new FXMLLoader(MainApplication.class.getResource("add-admin-view.fxml"));
        removeAdminView = new FXMLLoader((MainApplication.class.getResource("remove-admin-view.fxml")));

        // Definition des scènes de l'application 
        signIn = new Scene(signInView.load(), 400, 600);
        login = new Scene(loginView.load(), 400, 600);
        dashboard = new Scene(dashboardView.load(), 960, 540);
        profile = new Scene(profileView.load(), 400, 600);
        taskCreation = new Scene(taskCreationView.load(), 400, 600);
        categoryCreation = new Scene(categoryCreationView.load(), 400, 600);
        edit = new Scene(editView.load(), 400, 600);
        detail = new Scene(detailView.load(), 400, 600);
        addAdmin = new Scene(addAdminView.load(), 400, 600);
        removeAdmin = new Scene(removeAdminView.load(), 400, 600);

        //import style.css
        setStyle();

        // Si il n'y a pas encore d'utilisation enregistrer le premier doit s'inscrire 
        if(userManager.isEmpty())
        {
            stage.setScene(signIn);
        }
        else
        {
            stage.setScene(login);
        }
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        save.saveCategories();
        save.save();
    }

    public static void main(String[] args) throws IOException {
        save = new Save();
        categoryManager = new CategoryManager();
        save.loadCategories();
        userManager = save.load();
        launch();
    }
}
