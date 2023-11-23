package fr.pr70.project_pr70.back;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Task manager.
 */
public class TaskManager
{
    /**
     * The Tasks.
     */
    protected ArrayList<Task> tasks;


    /**
     * Instantiates a new Task manager.
     */
    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur par défaut initialisant une liste de tâches vide.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /* ----------------- Getters ----------------- */

    /**
     * Renvoie la liste des tâches actuellement gérées par le gestionnaire de tâches.
     * @return La liste des tâches
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /* ----------------- Methods ----------------- */

    /**
     * Crée une nouvelle tâche avec les détails fournis et l'ajoute à la liste de tâches.
     * @param _name Le nom de la tâche
     * @param _description La description de la tâche
     * @param _startDate La date de début de la tâche
     * @param _deadline La date limite de la tâche
     * @param _priority La priorité de la tâche
     * @param _category La catégorie de la tâche
     */
    public void createTask(String _name, String _description, Date _startDate, Date _deadline, Priority _priority, Category _category) {
        Task newTask = new Task(_name, _description, _startDate, _deadline, _priority, _category);
        tasks.add(newTask);
    }

    /**
     * Supprimer la tâche de la liste de tâches
     * @param _task ; tâche que l'utilisateur souhaite supprimer
     */
    public void deleteTask(Task _task)
    {
        tasks.removeIf(item -> item.equals(_task));
    }

}
