package fr.pr70.project_pr70.back;

import java.util.Date;

public class Task
{
    protected String name;
    protected String description;
    protected Date startDate;
    protected Date deadline;
    protected Priority priority;
    protected Category category;
    protected boolean completed;

    protected boolean reported;

    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur par défaut initialisant les valeurs par défaut
     */
    public Task()
    {
        name = "";
        description = "";
        startDate = new Date();
        deadline = new Date();
        priority = Priority.LOW;
        category = new Category();
        completed = false;
        reported = false;
    }

    /**
     * Constructeur avec des paramètres permettant d'initialiser les valeurs
     * @param _name : nom de la tâche
     * @param _description : description de la tâche
     * @param _startDate : date de depart de la tâche
     * @param _deadline : date de fin maximal de la tâche
     * @param _priority : status de priorité de la tâche
     * @param _category : categorie de la tâche
     */
    public Task(String _name, String _description, Date _startDate, Date _deadline, Priority _priority, Category _category)
    {
        name = _name;
        description = _description;
        startDate = _startDate;
        deadline = _deadline;
        priority = _priority;
        category = _category;
        completed = false;
        reported = false;
    }


    /* ----------------- Getters ----------------- */

    /**
     * Renvoie le nom de la tâche
     * @return l'attribut name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Renvoie la description de la tâche
     * @return l'attribut description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Renvoie le status de priorité de la tâche
     * @return l'attribut priority
     */
    public Priority getPriority()
    {
        return priority;
    }

    /**
     * Renvoie la catégorie de la tâche
     * @return l'attribut category
     */
    public Category getCategory()
    {
        return category;
    }

    /**
     * Renvoie la date de début de la tâche
     * @return l'attribut startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Renvoie la date de fin de la tâche
     * @return l'attribut deadline
     */
    public Date getDeadline()
    {
        return deadline;
    }


    /* ----------------- Setters ----------------- */

    /**
     * Met à jour l'état de complétion de la tâche
     */
    public void setCompleted(boolean _completed)
    {
        completed = _completed;
    }

    /**
     * Met à jour l'état de signalement  de la tâche
     */
    public void setReported(boolean _reported)
    {
        reported = _reported;
    }

    /**
     * Met à jour la date de début de la tâche
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Met à jour la date de fin de la tâche
     */
    public void setDeadline(Date _deadline)
    {
        deadline = _deadline;
    }

    /**
     * Met à jour la description de la tâche
     */
    public void setDescription(String _description)
    {
        description = _description;
    }

    /**
     * Met à jour le nom de la tâche
     */
    public void setName(String _name)
    {
        name = _name;
    }

    /**
     * Met à jour la priorité de la tâche
     */
    public void setPriority(Priority _priority)
    {
        priority = _priority;
    }

    /**
     * Met à jour la catégorie de la tâche
     */
    public void setCategory(Category _category)
    {
        category = _category;
    }

    /* ----------------- Methods ----------------- */

    /**
     * Calcul du pourcentage de temps restant
     * @return : pourcentage (entre 0 et 1)
     */
    public double getTimePercent()
    {
        long duration = deadline.getTime() - startDate.getTime();
        Date currentDate = new Date();
        long currentPoint = currentDate.getTime() - startDate.getTime();

        return (double)currentPoint / (double)duration;

    }

    /**
     * Revoie le status de la tâche
     * @return : boolean
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Donne l'information de si la tâche à été mise en avant par l'admin ou non
     * @return : boolean de mise en avant de la tâche
     */
    public boolean isReported()
    {
        return reported;
    }

    /**
     * Transforme en String les variables propre à la tâche
     */
    @Override
    public String toString()
    {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", completed=" + completed +
                '}';
    }
}
