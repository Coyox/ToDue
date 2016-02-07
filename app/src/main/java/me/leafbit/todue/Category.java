package me.leafbit.todue;

/**
 * Created by Adam on 2016-02-06.
 */
public class Category {

    String name;            // Name of category
    String hexColor;        // #RRGGBB color code;

    public Category(String name, String hexColor){
        this.name = name;
        this.hexColor = hexColor;
    }

    // Reads saved categories from storage and returns all
    public Category[] loadAllCategory(){
        //TODO:
        return new Category[0];
    }

    // Saves a category to storage
    public void saveCategory(Category c){
        //TODO:
    }

}
