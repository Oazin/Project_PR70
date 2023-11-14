package fr.pr70.project_pr70.back;

import java.util.Date;

public class Task
{
    protected String name;
    protected String description;
    protected Date startDate;
    protected Date deadline;
    protected Priority priority;
    protected boolean completed;

    /* ----------------- Constructor ----------------- */

    public Task()
    {
        name = "";
        description = "";
        startDate = new Date();
        deadline = new Date();
        priority = Priority.LOW;
        completed = false;
    }

    public Task(String _name, String _description, Date _startDate, Date _deadline, Priority _priority)
    {
        name = _name;
        description = _description;
        startDate = _startDate;
        deadline = _deadline;
        priority = _priority;
        completed = false;
    }


    /* ----------------- Getters ----------------- */

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public Boolean getStatus() { return completed; }

    public double getTimePercent()
    {
        long duration = deadline.getTime() - startDate.getTime();
        Date currentDate = new Date();
        long currentPoint = currentDate.getTime() - startDate.getTime();

        return (double)currentPoint / (double)duration;

    }




    /* ----------------- Setters ----------------- */

    public void setCompleted(boolean _completed)
    {
        completed = _completed;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(Date _deadline)
    {
        deadline = _deadline;
    }

    public void setDescription(String _description)
    {
        description = _description;
    }

    public void setName(String _name)
    {
        name = _name;
    }

    public void setPriority(Priority _priority)
    {
        priority = _priority;
    }




    /* ----------------- Methods ----------------- */

    /*! @brief : Revoie le status de la tâche
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /*! @brief : Transforme en String les variables propre à la tâche
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
