package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

public class Category {
    protected String name;
    protected Color color;
    public Category(String _name,Color _color)
    {
        name = _name;
        color = _color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
