package fr.pr70.project_pr70.back;

import fr.pr70.project_pr70.MainApplication;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The type Save.
 */
public class Save {
    /**
     * The Users data.
     */
    public File usersData;
    /**
     * The Categories data.
     */
    public File categoriesData;
    /**
     * The Tasks data.
     */
    public File tasksData;

    /**
     * Instantiates a new Save.
     *
     * @throws IOException the io exception
     */
    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur de la classe Save utilisé pour initialiser les fichiers de sauvegarde s'ils n'existent pas.
     * S'ils n'existent pas, crée les répertoires et les fichiers nécessaires pour la sauvegarde des données.
     * @throws IOException En cas d'erreur lors de la création des fichiers de sauvegarde
     */
    public Save() throws IOException {
        Path path = Paths.get("src/main/resources/fr/pr70/project_pr70/save/");
        Files.createDirectories(path);
        usersData = new File("src/main/resources/fr/pr70/project_pr70/save/users.data");
        if(!usersData.exists())
            if(!usersData.createNewFile())
                return;
        categoriesData = new File("src/main/resources/fr/pr70/project_pr70/save/categories.data");
        if(!categoriesData.exists())
            if(!categoriesData.createNewFile())
                return;
        tasksData = new File("src/main/resources/fr/pr70/project_pr70/save/tasks.data");
        if(!tasksData.exists())
            tasksData.createNewFile();
    }

    /**
     * Save tasks.
     *
     * @param writer      the writer
     * @param userId      the user id
     * @param taskManager the task manager
     * @throws IOException the io exception
     */
    /* ----------------- Methods ----------------- */
    /**
     * Sauvegarde les tâches de l'utilisateur dans un fichier
     * @param _writer ; Fichier dans lequel on sauvegarde les tâches
     * @param _userId ; Id de l'utilisateur dont on sauvegarde les tâches
     * @param _taskManager ; Gestionnaire de tâches de l'utilisateur
     */
    public void saveTasks(FileWriter _writer, int _userId, TaskManager _taskManager) throws IOException
    {
        for(Task task: _taskManager.getTasks())
        {
            String string = _userId+", "+task.getName()+", "+task.getDescription()+", "+task.getStartDate().getTime()+", "+task.getDeadline().getTime()+", "+task.getPriority()+", "+task.getCategory()+", "+task.isCompleted()+", "+task.isReported()+"\n";
            _writer.append(string);
        }
    }

    /**
     * Charge les tâches des utilisateurs depuis un fichier
     * @param _userManager ; Gestionnaire d'utilisateurs
     */
    public void loadTasks(UserManager _userManager) throws FileNotFoundException
    {
        if(tasksData == null) return;
        List<User> userList = _userManager.getUsers();
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
            task.setCategory(MainApplication.getCategoryManager().getCategory(taskInfo[6]));
            task.setCompleted(Boolean.parseBoolean(taskInfo[7]));
            task.setReported(Boolean.parseBoolean(taskInfo[8]));
            user.getTasks().getTasks().add(task);
        }
    }

    /**
     * Sauvegarde les utilisateurs dans un fichier
     * @param _userManager ; Gestionnaire d'utilisateurs
     */
    public void saveUsers(UserManager _userManager) throws IOException
    {
        if(usersData == null) return;
        FileWriter writer = new FileWriter(usersData);
        for(User user: _userManager.getUsers())
        {
            String string = user.getUsername()+", "+user.getPassword().getPasswordHash()+", "+user.isAdmin()+"\n";
            writer.append(string);
        }
        writer.close();
    }

    /**
     * Charge les utilisateurs depuis un fichier
     * @return Gestionnaire d'utilisateurs
     */
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

    /**
     * Sauvegarde les catégories dans un fichier
     * @param _categoryManager ; Gestionnaire de catégories
     */
    public void saveCategories(CategoryManager _categoryManager) throws IOException
    {
        if(categoriesData == null) return;
        FileWriter writer = new FileWriter(categoriesData);
        for(Category category: _categoryManager.getCategories())
        {
            String string = category.getName()+", "+category.getColor()+"\n";
            writer.append(string);
        }
        writer.close();
    }

    /**
     * Charge les catégories depuis un fichier
     * @return Gestionnaire de catégories
     */
    public CategoryManager loadCategories() throws FileNotFoundException
    {
        if(categoriesData == null) return null;
        CategoryManager categoryManager = new CategoryManager();
        Scanner scanner = new Scanner(categoriesData);
        while(scanner.hasNextLine())
        {
            String[] categoryInfo = scanner.nextLine().split(", ");
            Category category = new Category(categoryInfo[0], Color.web(categoryInfo[1]));
            categoryManager.addCategories(category);
        }
        return categoryManager;
    }

    /**
     * Sauvegarde les utilisateurs et les tâches dans des fichiers
     * @param _userManager ; Gestionnaire d'utilisateurs
     */
    public void save(UserManager _userManager) throws IOException
    {
        //save all tasks
        List<User> userList = _userManager.getUsers();
        FileWriter writer = new FileWriter(tasksData);
        for(int i = 0; i < userList.size(); i++)
        {
            saveTasks(writer, i, userList.get(i).getTasks());
        }
        writer.close();

        //save all users
        saveUsers(_userManager);
    }

    /**
     * Charge les utilisateurs et les tâches depuis des fichiers
     * @return Gestionnaire d'utilisateurs
     */
    public UserManager load() throws FileNotFoundException
    {
        //load all users
        UserManager userManager = loadUsers();
        //load all task
        loadTasks(userManager);
        return userManager;
    }
}
