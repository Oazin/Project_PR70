package fr.pr70.project_pr70;

import fr.pr70.project_pr70.back.*;
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

    /**
     * Renvoie l'utilisateur courant.
     * @return L'utilisateur courant
     */
    public static User getCurrentUser() {
        return userManager.getUser(currentUsername);
    }

    /**
     * Renvoie le gestionnaire d'utilisateurs de l'application.
     * @return Le gestionnaire d'utilisateurs
     */
    public static UserManager getUserManager() {
        return userManager;
    }

    /**
     * Renvoie le gestionnaire de catégories de l'application.
     * @return Le gestionnaire de catégories
     */
    public static CategoryManager getCategoryManager() {
        return categoryManager;
    }

    /* ----------------- Setters ----------------- */

    /**
     * Définit le nom d'utilisateur actuel.
     * @param _currentUsername Le nom d'utilisateur à définir comme actuel
     */
    public static void setCurrentUsername(String _currentUsername) {
        MainApplication.currentUsername = _currentUsername;
    }

    /* ----------------- Methods ----------------- */

    /**
     * Met à jour et affiche la scène du tableau de bord.
     * @behaviour:
     *   - Assigne le contrôleur à la vue
     *   - Met à jour les informations dynamiques du tableau de bord
     *   - Affiche la scène dans la fenêtre
     */
    public static void setDashboard() {
        DashboardController dashboardController = dashboardView.getController();
        dashboardController.createToolBar();
        dashboardController.updateTaskTable();
        stage.setScene(dashboard);
    }

    /**
     * Met à jour et affiche la scène du profil de l'utilisateur.
     * @behaviour:
     *   - Assigne le contrôleur à la vue
     *   - Met à jour les informations dynamiques du profil
     *   - Affiche la scène dans la fenêtre
     */
    public static void setProfile() {
        ProfileController profileController = profileView.getController();
        profileController.updateProfile();
        stage.setScene(profile);
    }

    /**
     * Met à jour et affiche la scène de création des tâches.
     * @behaviour:
     *   - Assigne le contrôleur à la vue
     *   - Met à jour les informations dynamiques de la page
     *   - Affiche la scène dans la fenêtre
     */
    public static void setTaskCreation() {
        TaskCreationController taskCreationController = taskCreationView.getController();
        taskCreationController.updateCategoryComboBox();
        stage.setScene(taskCreation);
    }


    /**
     * Affiche la scène de création de catégorie.
     */
    public static void setCategoryCreation() {
        stage.setScene(categoryCreation);
    }

    /**
     * Affiche la scène de connexion.
     */
    public static void setLogin() {
        stage.setScene(login);
    }

    /**
     * Affiche la scène d'inscription.
     */
    public static void setSignIn() {
        stage.setScene(signIn);
    }

    /**
     * Met à jour et affiche la scène qui affiche les détails de la tâche.
     *
     *  @behaviour:
     *   - Assigne le contrôleur à la vue
     *   - Met à jour les informations dynamiques de la page
     *   - Affiche la scène dans la fenêtre
     */
    public static void setDetail(Task _task) {
        DetailController detailController = detailView.getController();
        detailController.updateDetail(_task);
        stage.setScene(detail);
    }

    /**
     * Met à jour et affiche la scène de modification de tâche.
     *
     *  @behaviour:
     *   - Assigne le contrôleur à la vue
     *   - Met à jour les informations dynamiques de la page
     *   - Affiche la scène dans la fenêtre
     */
    public static void setEdit(Task _task) {
        EditController editController = editView.getController();
        editController.updateEdit(_task);
        stage.setScene(edit);
    }

    /**
     * Affiche la scène avec la liste des utilisateurs non administrateurs.
     */
    public static void setAddAdmin() {
        AddAdminController addAdminController = addAdminView.getController();
        addAdminController.updateUserComboBox();
        stage.setScene(addAdmin);
    }

    /**
     * Affiche la scène avec la liste des administrateurs.
     */
    public static void setRemoveAdmin() {
        RemoveAdminController removeAdminController = removeAdminView.getController();
        removeAdminController.updateAdminComboBox();
        stage.setScene(removeAdmin);
    }

    /**
     * Assigne le style au differente scene du logiciel
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


    /**
     * Set toutes les variables de l'application au demarrage
     */
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

    /**
     * Sauvegarde lorsque l'application est fermé
     * @throws IOException
     */
    @Override
    public void stop() throws IOException {
        save.saveCategories(categoryManager);
        save.save(userManager);
    }

    /**
     * Main de l'application qui set les variables static et lance l'application
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        save = new Save();
        categoryManager = save.loadCategories();
        userManager = save.load();
        launch();
    }
}
