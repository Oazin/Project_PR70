package fr.pr70.project_pr70.back;

import java.util.ArrayList;

public class UserManager {
    private final ArrayList<User> users;

    public UserManager(){
        this.users = new ArrayList<User>();
    }
    
    public ArrayList<User> getUsers(){
        return this.users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    public User getUser(String username){
        for (User user : this.users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    public boolean connectUser(String _user, String _password)
    {
        User user = getUser("_user");
        if (user.getPassword().checkPassword(_password))
        {
            user.setLoggedIn(true);
            return true;
        }
        return false;
    }
}
