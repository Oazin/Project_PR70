package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

/**
 * The type Category.
 */
public class Category {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Color.
     */
    protected Color color;

    /* ----------------- Constructor ----------------- */

    /**
     * Instantiates a new Category.
     */
    public Category()
    {
        name = "";
        color = Color.WHITE;
    }

    /**
     * Instantiates a new Category.
     *
     * @param _name  the name
     * @param _color the color
     */
    public Category(String _name,Color _color)
    {
        name = _name;
        color = _color;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    /* ----------------- Getters ----------------- */
    public String getName() {
        return name;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
