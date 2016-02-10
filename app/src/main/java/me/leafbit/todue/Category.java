package me.leafbit.todue;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Xml;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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


    // Reads saved categories from database and returns all
    public Category[] loadAllCategories(){
        //TODO:
        return new Category[0];
    }

    // Saves a category to SQLite database
    public long saveCategory(Category c, Context ctx){
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_NAME, c.id);
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_COLOR, c.hexColor);

        long newRowId;
        newRowId = db.insert(
                ToDueContract.CategoryEntry.TABLE_NAME,
                "null",
                values);

        return newRowId;
    }



}
