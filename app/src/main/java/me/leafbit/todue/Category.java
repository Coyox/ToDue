package me.leafbit.todue;

import android.content.Context;
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


    // Reads saved categories from storage and returns all
    public Category[] loadAllCategories(){
        //TODO:
        return new Category[0];
    }

    // Saves a category to storage by appending it to the end of the file
    public boolean saveCategory(Category c, Context ctx){
        //TODO: Consider a SQL database approach instead of xml
        //Source:
        //http://stackoverflow.com/questions/11687074/create-xml-file-and-save-it-in-internal-storage-android
        String fileName = "categories.xml";

        FileOutputStream fos;
        try {
            // Open data storage to append new category
            fos = ctx.openFileOutput(fileName, Context.MODE_APPEND);
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos,"UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            // Save category in XML format
            serializer.startTag(null, "category");

            serializer.startTag(null, "name");
            serializer.text(c.id);
            serializer.endTag(null,"name");

            serializer.startTag(null, "color");
            serializer.text(c.hexColor);
            serializer.endTag(null, "color");

            serializer.endDocument();
            serializer.flush();

            fos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
