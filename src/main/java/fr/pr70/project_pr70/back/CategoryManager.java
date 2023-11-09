package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class CategoryManager
{
    private static ArrayList<Category> categories;

    public static void Init()
    {
        if(categories == null)
        {
            categories = new ArrayList<>();
        }
    }

    public static void addCategories(Category _category) { CategoryManager.categories.add(_category); }

    public static ArrayList<Category> getCategories() { return categories; }

    public static void setCategories(ArrayList<Category> categories) { CategoryManager.categories = categories; }

}
