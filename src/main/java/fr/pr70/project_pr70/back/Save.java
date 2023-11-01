package fr.pr70.project_pr70.back;

import fr.pr70.project_pr70.MainApplication;

import java.sql.*;

public class Save {

    private static Connection connection;
    public static void Init() throws SQLException {
        connection = DriverManager.getConnection("localhost", "root", "");
    }
    public void saveUser(User user) throws SQLException {
        if(user == null)
        {
            return;
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username, password, admin) VALUES('"+user.getUsername()+"', '"+user.getPassword().getPasswordHash()+"', "+user.admin+");");
        ResultSet resultSet = preparedStatement.executeQuery();
    }

    public UserManager loadUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();
        UserManager userManager = MainApplication.getUserManager();
        while(resultSet.next())
        {
            User user = new User(resultSet.getString("username"), resultSet.getString("password"));
            if(resultSet.getString("admin").equals("true"))
            {
                user.setAdmin(true);
            }
            userManager.addUser(user);
        }
        return userManager;
    }

    public void saveTask(Task task)
    {

    }

    public Task loadTask()
    {
        Task task = new Task();
        return task;
    }
}
