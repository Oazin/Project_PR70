package fr.pr70.project_pr70;

import fr.pr70.project_pr70.back.CategoryManager;
import fr.pr70.project_pr70.back.UserManager;
import fr.pr70.project_pr70.front.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application
{
    private static UserManager userManager;

    private static String currentUsername;

    private static CategoryManager categoryManager;

    private static Stage stage;

    private static FXMLLoader loginView;

    private static FXMLLoader signInView;

    private static FXMLLoader dashboardView;

    private static FXMLLoader taskCreationView;

    private static FXMLLoader categoryCreationView;

    private static FXMLLoader profileView;

    private static Scene login;

    private static Scene signIn;
    private static Scene dashboard;

    private static Scene profile;

    private static Scene taskCreation;

    private static Scene categoryCreation;

    public static void setCurrentUsername(String _currentUsername) {
        MainApplication.currentUsername = _currentUsername;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static UserManager getUserManager()
    {
        return userManager;
    }

    public static CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public static void setDashboard()
    {
        DashboardController dashboardController = dashboardView.getController();
        dashboardController.updateTaskList();
        stage.setScene(dashboard);
    }
    public static void setProfile(){stage.setScene(profile);}

    public static void setTaskCreation() {stage.setScene(taskCreation);}

    public static void setCategoryCreation() {
        stage.setScene(categoryCreation);
    }

    public static void setLogin(){
        stage.setScene(login);
    }

    public static void setSignIn(){
        stage.setScene(signIn);
    }

    @Override
    public void start(Stage _stage) throws IOException
    {
        stage = _stage;
        stage.setTitle("YO Task Manager");

        signInView = new FXMLLoader(MainApplication.class.getResource("sign-in-view.fxml"));
        loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        dashboardView = new FXMLLoader(MainApplication.class.getResource("dashboard-view.fxml"));
        profileView = new FXMLLoader(MainApplication.class.getResource("profile-view.fxml"));
        taskCreationView = new FXMLLoader(MainApplication.class.getResource("task-creation-view.fxml"));
        categoryCreationView = new FXMLLoader(MainApplication.class.getResource("category-creation-view.fxml"));
        signIn = new Scene(signInView.load(), 400, 600);
        login = new Scene(loginView.load(), 400, 600);
        dashboard = new Scene(dashboardView.load(), 960, 540);

        profile = new Scene(profileView.load(), 400, 600);
        taskCreation = new Scene(taskCreationView.load(), 400, 600);
        categoryCreation = new Scene(categoryCreationView.load(), 400, 600);
        //import style.css
        URL url = this.getClass().getResource("style.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        dashboard.getStylesheets().add(css);
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

    public static void main(String[] args)
    {
        userManager = new UserManager();
        categoryManager = new CategoryManager();
        launch();
    }
}
