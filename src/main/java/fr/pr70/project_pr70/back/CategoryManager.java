package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class CategoryManager
{
    private static ArrayList<Category> categories;


    /* ----------------- Constructor ----------------- */
    public static void Init()
    {
        if(categories == null)
        {
            categories = new ArrayList<>();
        }
    }


    /* ----------------- Getter ----------------- */
    public static ArrayList<Category> getCategories() { return categories; }


    /* ----------------- Setter ----------------- */
    public static void setCategories(ArrayList<Category> categories) { CategoryManager.categories = categories; }


    /* ----------------- Methods ----------------- */
    /*! @brief : Ajout une categorie à la liste des categories
     */
    public static void addCategories(Category _category) { CategoryManager.categories.add(_category); }

}
