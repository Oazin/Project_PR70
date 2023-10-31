package fr.pr70.project_pr70.back;

import java.util.Date;

public class User
{
    protected String username;
    protected Password password;
    protected TaskManager tasks;
    protected boolean admin;
    protected boolean login;


    /* ----------------- Constructor ----------------- */

    public User(String _username, String _password)
    {
        username = _username;
        password = new Password(_password);
        tasks = new TaskManager();
    }

    /* ----------------- Getters ----------------- */

    public String getUsername()
    {
        return username;
    }

    public TaskManager getTasks()
    {
        return tasks;
    }

    public Password getPassword()
    {
        return password;
    }
    /* ----------------- Setters ----------------- */

    public void setPassword(Password _password)
    {
        password = _password;
    }

    public void setTasks(TaskManager _tasks)
    {
        tasks = _tasks;
    }

    public void setUsername(String _username)
    {
        username = _username;
    }


    /* ----------------- Methods ----------------- */

    /*! @brief : Vérifier si le mot de passe fourni correspond au mot de passe de l'utilisateur
     *  @param _username ; Nom d'utilisateur qui a été entrer
     *  @param _password ; mot de passe qui a été entrer
     */
    public boolean authenticate(String _username, String _password)
    {
        return getUsername().equals(_username) && password.checkPassword(_password);
    }


    /*! @brief : Appel la fonction createTask de TaskManager
     *  @param String _name ; chaîne de charactère correspondant au nom de la tâche
     *  @param _description ; chaîne de charactère correspondant à la description de la tâche
     *  @param _deadline ; Date de fin optimal de la tâche
     *  @param _priority ; Priorité associée à la tâche
     *
     *  @behaviour :
     *  Passer le gestionnaire de tâche associés à l'utilisateur pour
     *  créer et ajouter la tâche à la liste de l'utilisateur
     */
    public void addTask(String _name, String _description, Date _deadline, Priority _priority)
    {
        tasks.createTask(_name, _description, _deadline, _priority);
    }

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
    public void modifyTask(Task _task, String _name, String _description, Date _deadline, Priority _priority)
    {
        tasks.editTask(_task, _name, _description, _deadline, _priority);
    }

    /*! @brief : revoie true si l'utilisateur est admin
     */
    public boolean isAdmin()
    {
        return admin;
    }

    /*! @brief : revoie true si l'utilisateur est connecte
     */
    public boolean isLogin()
    {
        return login;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tasks=" + tasks +
                '}';
    }

    public void setAdmin(boolean _admin) {
        admin = _admin;
    }
}
