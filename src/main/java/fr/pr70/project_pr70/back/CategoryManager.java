package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public final class CategoryManager
{
    private ArrayList<Category> categories;


    /* ----------------- Constructor ----------------- */
    public CategoryManager()
    {
        categories = new ArrayList<>();
    }


    /* ----------------- Getter ----------------- */
    public ArrayList<Category> getCategories() { return categories; }


    /* ----------------- Setter ----------------- */
    public void setCategories(ArrayList<Category> _categories) { categories = _categories; }


    /* ----------------- Methods ----------------- */
    /*! @brief : Ajout une categorie à la liste des categories
     */

    public Category getCategory(String _categoryName)
    {
        for(Category category:categories)
        {
            if(category.name.equals(_categoryName))
            {
                return category;
            }
        }
        return null;
    }
    public void addCategories(Category _category) { categories.add(_category); }

}
