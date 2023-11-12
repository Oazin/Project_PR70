package fr.pr70.project_pr70.back;

import java.util.ArrayList;
import java.util.Date;

public class TaskManager
{
    protected ArrayList<Task> tasks;


    /* ----------------- Constructor ----------------- */
    public TaskManager()
    {
        tasks = new ArrayList<>();
    }


    /* ----------------- Getters ----------------- */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /* ----------------- Methods ----------------- */
    /*! @brief : Ajouter la tâche à la liste de tâches
     *  @param _task ; Tâche que l'utilisateur veux ajouter à sont gestionnaire
     */
    public void createTask(String _name, String _description,Date _startDate, Date _deadline, Priority _priority)
    {
        Task newTask = new Task(_name, _description, _startDate,_deadline, _priority);
        tasks.add(newTask);
    }

    /*! @brief : Modifie une tache déjà existante
     *  @param _tack ; tâche que l'utilisateur souhaite modifier
     *  @param _name ; chaîne de charactère correspondant au nom de la tâche
     *  @param _description ; chaîne de charactère correspondant a la description de la tâche
     *  @param _startDate ; Date de debut de la task
     *  @param _deadline ;  Deadline de la task
     *  @param _priority ; Priorité de la task
     */
    public void editTask(Task _task, String _name, String _description, Date _startDate, Date _deadline, Priority _priority)
    {
        if (_name != null) _task.setName(_name);
        if (_description != null) _task.setDescription(_description);
        if (_startDate != null) _task.setStartDate(_startDate);
        if (_deadline != null) _task.setDeadline(_deadline);
        if (_priority != null) _task.setPriority(_priority);
    }

    /*! @brief : Supprimer la tâche de la liste de tâches
     *  @param _tack ; tâche que l'utilisateur souhaite supprimer
     */
    public void deleteTask(Task _task)
    {
        tasks.removeIf(item -> item.equals(_task));
    }

    /*! @brief : Marquer la tâche comme terminée ou non terminée
     *  @param _tack ; tâche que l'utilisateur a complété
     */
    public void markTaskAsCompleted(Task _task)
    {
        for (Task t : tasks)
        {
            if (t.equals(_task))
            {
                t.setCompleted(true);
            }
        }
    }
}
