package fr.pr70.project_pr70;

import fr.pr70.project_pr70.back.UserManager;
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

    private static Stage stage;

    private static Scene login;

    private static Scene signIn;
    private static Scene dashboard;

    private static Scene profile;

    private static Scene taskCreation;

    public static UserManager getUserManager()
    {
        return userManager;
    }

    public static void setDashboard()
    {
        stage.setScene(dashboard);
    }
    public static void setProfile(){stage.setScene(profile);}

    public static void setTaskCreation() {stage.setScene(taskCreation);}

    @Override
    public void start(Stage _stage) throws IOException
    {
        stage = _stage;
        stage.setTitle("Gestionnaire de tache personnel");
        FXMLLoader signInView = new FXMLLoader(MainApplication.class.getResource("sign-in-view.fxml"));
        FXMLLoader loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        FXMLLoader dashboardView = new FXMLLoader(MainApplication.class.getResource("dashboard-view.fxml"));

        // FXMLLoader profileView = new FXMLLoader(MainApplication.class.getResource("profile-view.fxml"));

        FXMLLoader taskCreationView = new FXMLLoader(MainApplication.class.getResource("task-creation-view.fxml"));
        signIn = new Scene(signInView.load(), 400, 600);
        login = new Scene(loginView.load(), 400, 600);
        dashboard = new Scene(dashboardView.load(), 960, 540);

        // profile = new Scene(profileView.load(), 400, 600);
        taskCreation = new Scene(taskCreationView.load(), 400, 600);
        //import style.css
        URL url = this.getClass().getResource("style.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        dashboard.getStylesheets().add(css);

        //stage.setScene(profile);
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
        launch();
    }
}
