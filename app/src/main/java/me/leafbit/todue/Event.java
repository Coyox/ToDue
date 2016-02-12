package me.leafbit.todue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    String hexColor;

    public Event(String name, String dueDate, String createdDate, String category){
        this.name = name;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.category = category;
    }




    // Read the saved event data from storage and return all
    public ArrayList<Event> loadAllEvents(Context ctx){
        //TODO:
        ArrayList<Event> events = new ArrayList<Event>();
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //
        String[] projection = {
                ToDueContract.EventEntry.COLUMN_NAME_EVENT_NAME,
                ToDueContract.EventEntry.COLUMN_NAME_DUE,
                ToDueContract.EventEntry.COLUMN_NAME_START,
                ToDueContract.CategoryEntry.COLUMN_NAME_NAME
        };

        String sortOrder = ToDueContract.EventEntry.COLUMN_NAME_DUE; //TODO: Determine proper sort
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
                    String name = c.getString(c.getColumnIndex(ToDueContract.EventEntry.COLUMN_NAME_EVENT_NAME));
                    String dueDate = c.getString(c.getColumnIndex(ToDueContract.EventEntry.COLUMN_NAME_DUE));
                    String createdDate = c.getString(c.getColumnIndex(ToDueContract.EventEntry.COLUMN_NAME_START));
                    String cName = c.getString(c.getColumnIndex(ToDueContract.CategoryEntry.COLUMN_NAME_NAME));
                    //Category category = new Category(name, color);
                    Event event = new Event(name, dueDate, createdDate, cName);
                    events.add(event);
                    //categories.add(category);
                } while (c.moveToNext());
            }
        }

        return events;
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

        System.out.println("DEBUG: Saved event at " + newRowId);
        return newRowId;

    }






}
