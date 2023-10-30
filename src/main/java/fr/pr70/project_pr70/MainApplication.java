package fr.pr70.project_pr70;

import fr.pr70.project_pr70.back.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application
{
    private static UserManager userManager;

    private static Stage stage;

    private static Scene login;

    private static Scene signIn;
    private static Scene dashboard;

    public static UserManager getUserManager()
    {
        return userManager;
    }

    public static void setDashboard()
    {
        stage.setScene(dashboard);
    }

    @Override
    public void start(Stage _stage) throws IOException
    {
        stage = _stage;
        FXMLLoader loginView = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        FXMLLoader dashboardView = new FXMLLoader(MainApplication.class.getResource("dashboard-view.fxml"));
        login = new Scene(loginView.load(), 400, 600);
        dashboard = new Scene(dashboardView.load(), 1920, 1080);
        stage.setTitle("MainApp");
        stage.setScene(login);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
        userManager = new UserManager();
    }
}
