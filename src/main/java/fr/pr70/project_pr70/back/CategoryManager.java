package fr.pr70.project_pr70.back;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * The type Category manager.
 */
public final class CategoryManager
{
    private ArrayList<Category> categories;


    /**
     * Instantiates a new Category manager.
     */
    /* ----------------- Constructor ----------------- */
    public CategoryManager()
    {
        categories = new ArrayList<>();
    }


    /**
     * Gets categories.
     *
     * @return the categories
     */
    /* ----------------- Getter ----------------- */
    public ArrayList<Category> getCategories() { return categories; }


    /**
     * Sets categories.
     *
     * @param _categories the categories
     */
    /* ----------------- Setter ----------------- */
    public void setCategories(ArrayList<Category> _categories) { categories = _categories; }


    /* ----------------- Methods ----------------- */
    /*! @brief : Ajout une categorie à la liste des categories
     */

    /**
     * Gets category.
     *
     * @param _categoryName the category name
     * @return the category
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

    /**
     * Add categories.
     *
     * @param _category the category
     */
    public void addCategories(Category _category) { categories.add(_category); }

}
