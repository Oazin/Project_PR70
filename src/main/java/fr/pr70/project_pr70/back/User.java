package fr.pr70.project_pr70.back;

import java.util.Date;

/**
 * The type User.
 */
public class User
{
    /**
     * The Username.
     */
    protected String username;
    /**
     * The Password.
     */
    protected Password password;
    /**
     * The Tasks.
     */
    protected TaskManager tasks;
    /**
     * The Admin.
     */
    protected boolean admin;
    /**
     * The Connected.
     */
    protected boolean connected;



    /* ----------------- Constructor ----------------- */

    /**
     * Instantiates a new User.
     *
     * @param _username the username
     */
    public User(String _username)
    {
        username = _username;
        password = new Password("");
        tasks = new TaskManager();
    }

    /**
     * Instantiates a new User.
     *
     * @param _username the username
     * @param _password the password
     */
    public User(String _username, String _password)
    {
        username = _username;
        password = new Password(_password);
        tasks = new TaskManager();
    }


    /* ----------------- Getters ----------------- */

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public TaskManager getTasks()
    {
        return tasks;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public Password getPassword()
    {
        return password;
    }


    /* ----------------- Setters ----------------- */

    /**
     * Sets password.
     *
     * @param _password the password
     */
    public void setPassword(Password _password)
    {
        password = _password;
    }

    /**
     * Sets tasks.
     *
     * @param _tasks the tasks
     */
    public void setTasks(TaskManager _tasks)
    {
        tasks = _tasks;
    }

    /**
     * Sets admin.
     *
     * @param _admin the admin
     */
    public void setAdmin(boolean _admin)
    {
        admin = _admin;
    }

    /**
     * Sets connected.
     *
     * @param _connected the connected
     */
    public void setConnected(boolean _connected)
    {
        connected = _connected;
    }


    /* ----------------- Methods ----------------- */

    /**
     * Change password.
     *
     * @param _password the password
     */
    /*! @brief : Change le mot de passe de l'utilisateur
     *  @param _password ; mot de passe qui a été entrer
     *
     *  @behaviour :
     *  Créer un nouveau mot de passe avec la chaîne de charactère entrée
     *  et l'associe à l'utilisateur
     */
    public void changePassword(String _password)
    {
        password = new Password(_password);
    }

    /**
     * Authenticate boolean.
     *
     * @param _username the username
     * @param _password the password
     * @return the boolean
     */
    /*! @brief : Vérifier si le mot de passe fourni correspond au mot de passe de l'utilisateur
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *  @param _password ; mot de passe qui a été entrer
     */
    public boolean authenticate(String _username, String _password)
    {
        return getUsername().equals(_username) && password.checkPassword(_password);
    }

    /**
     * Is connected boolean.
     *
     * @return the boolean
     */
    /*! @brief : revoie true si l'utilisateur est connecte
     */
    public boolean isConnected()
    {
        return connected;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    /*! @brief : revoie true si l'utilisateur est admin
    */
    public boolean isAdmin()
    {
        return admin;
    }


    /**
     * Add task.
     *
     * @param _name        the name
     * @param _description the description
     * @param _startDate   the start date
     * @param _deadline    the deadline
     * @param _priority    the priority
     * @param _category    the category
     */
    /*! @brief : Appel la fonction createTask de TaskManager
    *  @param String _name ; chaîne de charactère correspondant au nom de la tâche
    *  @param _description ; chaîne de charactère correspondant à la description de la tâche
    *  @param _startDate ; Date de debut optimal de la tâche
    *  @param _deadline ; Date de fin optimal de la tâche
    *  @param _priority ; Priorité associée à la tâche
    *
    *  @behaviour :
    *  Passer le gestionnaire de tâche associés à l'utilisateur pour
    *  créer et ajouter la tâche à la liste de l'utilisateur
    */
    public void addTask(String _name, String _description,Date _startDate, Date _deadline, Priority _priority, Category _category)
    {
        tasks.createTask(_name, _description, _startDate, _deadline, _priority, _category);
    }

    /**
     * Remove task.
     *
     * @param _task the task
     */
    /*! @brief : Appel la fonction deleteTask de TaskManager
    *  @param _tack ; tâche que l'utilisateur souhaite supprimer
    *
    *  @behaviour :
    *  Passer le gestionnaire de tâche associés à l'utilisateur pour
    *  supprimer la tâche à la liste de l'utilisateur
    */
    public void removeTask(Task _task)
    {
        tasks.deleteTask(_task);
    }

    /**
     * Mark task as completed.
     *
     * @param _task the task
     */
    /*! @brief : Appel la fonction markTaskAsCompleted de TaskManager
     *  @param _tack ; tâche que l'utilisateur a complété
     *
     *  @behaviour :
     *  Passer le gestionnaire de tâche associés à l'utilisateur pour
     *  valider la complétion de la tâche à la liste de l'utilisateur
     */
    public void markTaskAsCompleted(Task _task)
    {
        tasks.markTaskAsCompleted(_task);
    }

    /**
     * Modify task.
     *
     * @param _task        the task
     * @param _name        the name
     * @param _description the description
     * @param _startDate   the start date
     * @param _deadline    the deadline
     * @param _priority    the priority
     */
    /*! @brief : Appel la fonction editTask de TaskManager
     *  @param _tack ; tâche que l'utilisateur a complété
     *  @param String _name ; chaîne de charactère correspondant au nom de la tâche
     *  @param _description ; chaîne de charactère correspondant à la description de la tâche
     *  @param _deadline ; Date de fin optimal de la tâche
     *  @param _priority ; Priorité associée à la tâche
     *
     *  @behaviour :
     *  Passer le gestionnaire de tâche associés à l'utilisateur pour
     *  modifier la tâche à la liste de l'utilisateur
     */
    public void modifyTask(Task _task, String _name, String _description, Date _startDate, Date _deadline, Priority _priority)
    {
        tasks.editTask(_task, _name, _description, _startDate,_deadline, _priority);
    }
 
    /* ----------------- Override ----------------- */
    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tasks=" + tasks +
                '}';
    }

}
