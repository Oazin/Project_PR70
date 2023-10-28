package fr.pr70.project_pr70.back;

import java.util.ArrayList;

public final class CategoryManager {
    private static ArrayList<Category> categories;

    public CategoryManager(){
        categories = new ArrayList<>();
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static void setCategories(ArrayList<Category> categories) {
        CategoryManager.categories = categories;
    }


}
