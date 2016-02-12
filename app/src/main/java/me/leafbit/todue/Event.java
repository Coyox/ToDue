package me.leafbit.todue;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 2016-02-06.
 */
public class Event {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");

    String name;
    String dueDate; // When the event is due. Includes YEAR, MONTH, DAY_OF_MONTH, AM_PM, HOUR
    String createdDate; // When the event was created. Same fields as above
    String category;

    public Event(String name, String dueDate, String createdDate, String category){
        this.name = name;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.category = category;
    }




    // Read the saved event data from storage and return all
    public Event[] loadAllEvents(){
        //TODO:
        return new Event[0];
    }

    // Save an event to storage
    public long saveEvent(Event e, Context ctx){
        // Instantiate Database
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Select columns for value insertion
        ContentValues values = new ContentValues();
        values.put(ToDueContract.EventEntry.COLUMN_NAME_EVENT_NAME, e.name); //name
        values.put(ToDueContract.EventEntry.COLUMN_NAME_DUE, e.dueDate); //duedate
        values.put(ToDueContract.EventEntry.COLUMN_NAME_START, e.createdDate); //createddate
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_NAME, e.category); // category name

        long newRowId;
        newRowId = db.insert(
                ToDueContract.EventEntry.TABLE_NAME,
                "null",
                values
        );

        return newRowId;

    }






}
