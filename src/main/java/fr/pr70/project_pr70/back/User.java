package fr.pr70.project_pr70.back;

import fr.pr70.project_pr70.back.Password;

public final class User {
    private String username;
    private final Password password;
    private TaskManager taskManager;
    private boolean isLoggedIn;
    private boolean isAdmin;


    public User(String _username, String _password){
        username = _username;
        password = new Password(_password);
        taskManager = new TaskManager();
        isLoggedIn = false;
        isAdmin = false;
    }

    // Getters
    public String getUsername(){
        return username;
    }

    public Password getPassword(){
        return password;
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    // Setters
    public void setUsername(String _username){
        username = _username;
    }

    public void setPassword(String password){
        this.password.setPassword(password);
    }

    public void setLoggedIn(boolean isLoggedIn){
        this.isLoggedIn = isLoggedIn;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public static void main(String[] args)
    {
        User user = new User("John Doe",  "password");
        System.out.println(user.getUsername()); // John Doe
        System.out.println(user.getPassword().checkPassword("password")); // true
    }
}