package me.leafbit.todue;

import android.graphics.Color;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 2016-02-06.
 */
public class Event {

    Calendar dueDate; // When the event is due. Includes YEAR, MONTH, DAY_OF_MONTH, AM_PM, HOUR
    Calendar createdDate; // When the event was created. Same fields as above
    Category category; // User defined category for this event


    public Event(Calendar dueDate, Calendar createdDate, Category category){
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
    public void saveEvent(Event e){
        //TODO:
    }






}
