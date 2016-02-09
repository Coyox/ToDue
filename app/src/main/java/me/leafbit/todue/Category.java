package me.leafbit.todue;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

/**
 * Created by Adam on 2016-02-06.
 */
public class Category {

    String id;            // Name of category
    String hexColor;        // #RRGGBB color code;

    public static int DEFAULT_COLOR_R = 10;
    public static int DEFAULT_COLOR_G = 100;
    public static int DEFAULT_COLOR_B = 50;

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
