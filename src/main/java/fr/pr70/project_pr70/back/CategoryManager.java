package fr.pr70.project_pr70.back;

import java.util.ArrayList;

public final class CategoryManager
{
    private ArrayList<Category> categories;


    /* ----------------- Constructor ----------------- */

    /**
     * Constructeur par défaut initialisant une liste de catégories vide.
     */
    public CategoryManager() {
        categories = new ArrayList<>();
    }

    /* ----------------- Getter ----------------- */

    /**
     * Renvoie la liste des catégories actuellement gérées.
     * @return La liste des catégories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /* ----------------- Methods ----------------- */

    /**
     * Renvoie la catégorie correspondant au nom spécifié.
     * @param _categoryName Le nom de la catégorie recherchée
     * @return La catégorie correspondante ou null si aucune correspondance n'est trouvée
     */
    public Category getCategory(String _categoryName) {
        for(Category category : categories) {
            if(category.getName().equals(_categoryName)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Ajoute une catégorie à la liste des catégories gérées.
     * @param _category La catégorie à ajouter
     */
    public void addCategories(Category _category) {
        categories.add(_category);
    }


}
