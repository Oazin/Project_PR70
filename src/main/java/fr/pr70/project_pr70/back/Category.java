package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

public class Category {
    protected String name;
    protected Color color;

    /* ----------------- Constructor ----------------- */

    /**
     * Représente une catégorie pour les tâches avec un nom et une couleur.
     */
    public Category()
    {
        name = "";
        color = Color.WHITE;
    }

    /**
     * Constructeur permettant d'initialiser la catégorie avec un nom et une couleur donnés.
     * @param _name Le nom de la catégorie
     * @param _color La couleur associée à la catégorie
     */
    public Category(String _name,Color _color)
    {
        name = _name;
        color = _color;

    }

    /* ----------------- Getters ----------------- */

    /**
     * Renvoie le nom de la catégorie.
     * @return Le nom de la catégorie
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie la couleur de la catégorie.
     * @return La couleur de la catégorie
     */
    public Color getColor() {
        return color;
    }

    /**
     * Transforme en String les variables propre à la tâche
     * @return Le nom de la catégorie en chaîne de caratère
     */
    @Override
    public String toString()
    {
        return name;
    }
}
