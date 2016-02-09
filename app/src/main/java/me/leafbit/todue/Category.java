package me.leafbit.todue;

/**
 * Created by Adam on 2016-02-06.
 */
public class Category {

    String id;            // Name of category
    String hexColor;        // #RRGGBB color code;

    public Category(String id, String hexColor){
        this.id = id;
        this.hexColor = hexColor;
    }

    // Reads saved categories from storage and returns all
    public Category[] loadAllCategories(){
        //TODO:
        return new Category[0];
    }

    // Saves a category to storage by appending it to the end of the file
    public void saveCategory(Category c){
        //TODO:
    }

}
