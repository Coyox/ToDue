package me.leafbit.todue;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import java.util.Calendar;
import java.sql.Date;

/**
 * Created by Adam on 2016-02-06.
 */
public class Event {

    String name;
    Calendar dueDate; // When the event is due. Includes YEAR, MONTH, DAY_OF_MONTH, AM_PM, HOUR
    Calendar createdDate; // When the event was created. Same fields as above
    Category category; // User defined category for this event

    // alternate types for dates
    Date dateDue;
    Date dateCreated;



    public Event(String name, Calendar dueDate, Calendar createdDate, Category category){
        this.name = name;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.category = category;
    }

    public Event(String name, Date dateDue, Date dateCreated, Category category){
        this.name = name;
        this.dateDue = dateDue;
        this.dateCreated = dateCreated;
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
        values.put(ToDueContract.EventEntry.COLUMN_NAME_DUE, e.dateCreated.getTime()); //duedate
        values.put(ToDueContract.EventEntry.COLUMN_NAME_START, e.dateCreated.getTime()); //createddate
        values.put(ToDueContract.CategoryEntry.COLUMN_NAME_NAME, e.category.id); // category name

        long newRowId;
        newRowId = db.insert(
                ToDueContract.EventEntry.TABLE_NAME,
                "null",
                values
        );

        return newRowId;

    }






}
