package fr.pr70.project_pr70.back;

import java.util.Date;

public class User
{
    protected String username;
    protected Password password;
    protected TaskManager tasks;
    protected boolean admin;
    protected boolean connected;



    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur pour créer un utilisateur avec un nom d'utilisateur donné.
     * Initialise l'utilisateur avec un nom d'utilisateur et un mot de passe par défaut.
     * @param _username Le nom d'utilisateur de l'utilisateur
     */
    public User(String _username) {
        username = _username;
        password = new Password("");
        tasks = new TaskManager();
    }

    /**
     * Constructeur pour créer un utilisateur avec un nom d'utilisateur et un mot de passe donnés.
     * Initialise l'utilisateur avec un nom d'utilisateur et un mot de passe spécifié.
     * @param _username Le nom d'utilisateur de l'utilisateur
     * @param _password Le mot de passe de l'utilisateur
     */
    public User(String _username, String _password) {
        username = _username;
        password = new Password(_password);
        tasks = new TaskManager();
    }

    /* ----------------- Getters ----------------- */

    /**
     * Renvoie le nom d'utilisateur de l'utilisateur.
     * @return Le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Renvoie le gestionnaire de tâches de l'utilisateur.
     * @return Le gestionnaire de tâches de l'utilisateur
     */
    public TaskManager getTasks() {
        return tasks;
    }

    /**
     * Renvoie le mot de passe de l'utilisateur.
     * @return Le mot de passe de l'utilisateur
     */
    public Password getPassword() {
        return password;
    }

    /* ----------------- Setters ----------------- */

    /**
     * Met à jour le statut admin de l'utilisateur.
     * @param _admin Valeur booléenne pour le statut admin
     */
    public void setAdmin(boolean _admin) {
        admin = _admin;
    }

    /**
     * Met à jour le statut de connexion de l'utilisateur.
     * @param _connected Valeur booléenne pour le statut de connexion
     */
    public void setConnected(boolean _connected) {
        connected = _connected;
    }



    /* ----------------- Methods ----------------- */
    
    /**
     * Change le mot de passe de l'utilisateur
     * @param _password ; mot de passe qui a été entrer
     *
     * @behaviour :
     * Créer un nouveau mot de passe avec la chaîne de charactère entrée
     * et l'associe à l'utilisateur
     */
    public void changePassword(String _password)
    {
        password = new Password(_password);
    }
    
    /**
     * Revoie true si l'utilisateur est admin
     * @return un boolean si l''utilisateur est administrateur
    */
    public boolean isAdmin()
    {
        return admin;
    }
    
    
    /**
     * Appel la fonction createTask de TaskManager
    *  @param _name ; chaîne de charactère correspondant au nom de la tâche
    *  @param _description ; chaîne de charactère correspondant à la description de la tâche
    *  @param _startDate ; Date de debut optimal de la tâche
    *  @param _deadline ; Date de fin optimal de la tâche
    *  @param _priority ; Priorité associée à la tâche
    *  @behaviour :
    *  Passer le gestionnaire de tâche associés à l'utilisateur pour
    *  créer et ajouter la tâche à la liste de l'utilisateur
    */
    public void addTask(String _name, String _description,Date _startDate, Date _deadline, Priority _priority, Category _category)
    {
        tasks.createTask(_name, _description, _startDate, _deadline, _priority, _category);
    }
    
    /**
     * Appel la fonction deleteTask de TaskManager
     *  @param _task ; tâche que l'utilisateur souhaite supprimer
     *
     *  @behaviour :
     *  Passer le gestionnaire de tâche associés à l'utilisateur pour
     *  supprimer la tâche à la liste de l'utilisateur
     */
    public void removeTask(Task _task)
    {
        tasks.deleteTask(_task);
    }

 
    /* ----------------- Override ----------------- */
    /**
     * Transforme en String les variables propre à l'utilisateur
     */
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
