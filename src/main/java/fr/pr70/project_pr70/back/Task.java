package fr.pr70.project_pr70.back;

import java.util.Date;

/**
 * The type Task.
 */
public class Task
{
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Start date.
     */
    protected Date startDate;
    /**
     * The Deadline.
     */
    protected Date deadline;
    /**
     * The Priority.
     */
    protected Priority priority;
    /**
     * The Category.
     */
    protected Category category;
    /**
     * The Completed.
     */
    protected boolean completed;

    /**
     * The Reported.
     */
    protected boolean reported;

    /* ----------------- Constructor ----------------- */

    /**
     * Instantiates a new Task.
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
     * Instantiates a new Task.
     *
     * @param _name        the name
     * @param _description the description
     * @param _startDate   the start date
     * @param _deadline    the deadline
     * @param _priority    the priority
     * @param _category    the category
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
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public Priority getPriority()
    {
        return priority;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory()
    {
        return category;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets deadline.
     *
     * @return the deadline
     */
    public Date getDeadline()
    {
        return deadline;
    }


    /* ----------------- Setters ----------------- */

    /**
     * Sets completed.
     *
     * @param _completed the completed
     */
    public void setCompleted(boolean _completed)
    {
        completed = _completed;
    }

    /**
     * Sets reported.
     *
     * @param _reported the reported
     */
    public void setReported(boolean _reported)
    {
        reported = _reported;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets deadline.
     *
     * @param _deadline the deadline
     */
    public void setDeadline(Date _deadline)
    {
        deadline = _deadline;
    }

    /**
     * Sets description.
     *
     * @param _description the description
     */
    public void setDescription(String _description)
    {
        description = _description;
    }

    /**
     * Sets name.
     *
     * @param _name the name
     */
    public void setName(String _name)
    {
        name = _name;
    }

    /**
     * Sets priority.
     *
     * @param _priority the priority
     */
    public void setPriority(Priority _priority)
    {
        priority = _priority;
    }

    /**
     * Sets category.
     *
     * @param _category the category
     */
    public void setCategory(Category _category)
    {
        category = _category;
    }

    /* ----------------- Methods ----------------- */

    /**
     * Gets time percent.
     *
     * @return the time percent
     */
    public double getTimePercent()
    {
        long duration = deadline.getTime() - startDate.getTime();
        Date currentDate = new Date();
        long currentPoint = currentDate.getTime() - startDate.getTime();

        return (double)currentPoint / (double)duration;

    }

    /**
     * Is completed boolean.
     *
     * @return the boolean
     */
    /*! @brief : Revoie le status de la tâche
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Is reported boolean.
     *
     * @return the boolean
     */
    public boolean isReported()
    {
        return reported;
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
