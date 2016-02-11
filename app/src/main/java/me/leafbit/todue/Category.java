package me.leafbit.todue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Xml;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
    public static ArrayList<Category> loadAllCategories(Context ctx){
        //TODO:
        ArrayList<Category> categories = new ArrayList<Category>();
        // Instantiate Database
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //
        String[] projection = {
                ToDueContract.CategoryEntry.COLUMN_NAME_NAME,
                ToDueContract.CategoryEntry.COLUMN_NAME_COLOR
        };

        String sortOrder = ToDueContract.CategoryEntry.COLUMN_NAME_NAME;
        // Return all entries in new cursor object
        Cursor c = db.query(ToDueContract.CategoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        if(c != null){
            if (c.moveToFirst()){
                do {
                    String name = c.getString(c.getColumnIndex(ToDueContract.CategoryEntry.COLUMN_NAME_NAME));
                    String color = c.getString(c.getColumnIndex(ToDueContract.CategoryEntry.COLUMN_NAME_COLOR));
                    Category category = new Category(name, color);
                    categories.add(category);
                } while (c.moveToNext());
            }
        }

        return categories;
    }

    // Saves a category to SQLite database
    public long saveCategory(Category c, Context ctx){
        // Instantiate Database
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Select columns for value insertion
        ContentValues values = new ContentValues();
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_NAME, c.id);
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_COLOR, c.hexColor);

        // Insert values and get back the id of the new row
        long newRowId;
        newRowId = db.insert(
                ToDueContract.CategoryEntry.TABLE_NAME,
                "null",
                values);

        return newRowId;
    }



}
