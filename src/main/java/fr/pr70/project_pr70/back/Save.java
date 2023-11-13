package fr.pr70.project_pr70.back;

import fr.pr70.project_pr70.MainApplication;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Save {
    public File usersData;
    public File categoriesData;
    public File tasksData;

    public Save() throws IOException {
        usersData = new File("src/main/resources/fr/pr70/project_pr70/users.data");
        if(!usersData.exists())
            if(!usersData.createNewFile())
                return;
        categoriesData = new File("src/main/resources/fr/pr70/project_pr70/categories.data");
        if(!categoriesData.exists())
            if(!categoriesData.createNewFile())
                return;
        tasksData = new File("src/main/resources/fr/pr70/project_pr70/tasks.data");
        if(!tasksData.exists())
            tasksData.createNewFile();
    }

    public void saveTask(int userId, Task task) throws IOException
    {
        if(tasksData == null) return;
        String string = userId+", "+task.getName()+", "+task.getDescription()+", "+task.getStartDate()+", "+task.getDeadline()+", "+task.getPriority()+", "+task.isCompleted()+"\n";
        FileWriter writer = new FileWriter(tasksData);
        writer.append(string);
        writer.close();
    }

    public void saveTasks(FileWriter writer, int userId, TaskManager taskManager) throws IOException
    {
        for(Task task: taskManager.getTasks())
        {
            String string = userId+", "+task.getName()+", "+task.getDescription()+", "+task.getStartDate().getTime()+", "+task.getDeadline().getTime()+", "+task.getPriority()+", "+task.isCompleted()+"\n";
            writer.append(string);
        }
    }

    public void loadTasks(UserManager userManager) throws FileNotFoundException
    {
        if(tasksData == null) return;
        List<User> userList = userManager.getUsers();
        Scanner scanner = new Scanner(tasksData);
        while(scanner.hasNextLine())
        {
            String[] taskInfo = scanner.nextLine().split(", ");
            User user = userList.get(Integer.parseInt(taskInfo[0]));
            Task task = new Task();
            task.setName(taskInfo[1]);
            task.setDescription(taskInfo[2]);
            task.setStartDate(new Date(Long.parseLong(taskInfo[3])));
            task.setDeadline(new Date(Long.parseLong(taskInfo[4])));
            task.setPriority(Priority.valueOf(taskInfo[5]));
            task.setCompleted(Boolean.parseBoolean(taskInfo[6]));
            user.getTasks().getTasks().add(task);
        }
    }

    public void saveUsers(UserManager userManager) throws IOException
    {
        if(usersData == null) return;
        FileWriter writer = new FileWriter(usersData);
        for(User user: userManager.getUsers())
        {
            String string = user.getUsername()+", "+user.getPassword().getPasswordHash()+", "+user.isAdmin()+"\n";
            writer.append(string);
        }
        writer.close();
    }

    public UserManager loadUsers() throws FileNotFoundException
    {
        if(usersData == null) return null;
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(usersData);
        while(scanner.hasNextLine())
        {
            String[] userInfo = scanner.nextLine().split(", ");
            User user = new User(userInfo[0]);
            user.getPassword().setPasswordHash(userInfo[1]);
            user.setAdmin(Boolean.parseBoolean(userInfo[2]));
            userManager.addUser(user);
        }
        return userManager;
    }

    public void saveCategories() throws IOException
    {
        if(categoriesData == null) return;
        FileWriter writer = new FileWriter(categoriesData);
        for(Category category: MainApplication.getCategoryManager().getCategories())
        {
            String string = category.getName()+", "+category.getColor()+"\n";
            writer.append(string);
        }
        writer.close();
    }

    public void loadCategories() throws FileNotFoundException
    {
        if(categoriesData == null) return;
        Scanner scanner = new Scanner(categoriesData);
        while(scanner.hasNextLine())
        {
            String[] categoryInfo = scanner.nextLine().split(", ");
            Category category = new Category(categoryInfo[0], Color.web(categoryInfo[1]));
            MainApplication.getCategoryManager().addCategories(category);
        }
    }

    public void save() throws IOException
    {
        UserManager userManager = MainApplication.getUserManager();
        //save all tasks
        List<User> userList = userManager.getUsers();
        FileWriter writer = new FileWriter(tasksData);
        for(int i = 0; i < userList.size(); i++)
        {
            saveTasks(writer, i, userList.get(i).getTasks());
        }
        writer.close();

        //save all users
        saveUsers(userManager);
    }

    public UserManager load() throws FileNotFoundException
    {
        //load all users
        UserManager userManager = loadUsers();
        //load all task
        loadTasks(userManager);
        return userManager;
    }
}
