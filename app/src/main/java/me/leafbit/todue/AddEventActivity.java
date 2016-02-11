package me.leafbit.todue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //load all categories and display them in the list
        ArrayList<Category> categories = Category.loadAllCategories(this);
        System.out.println("LOADED: " + categories.size() + " categories from database");
        ListView categoryView = (ListView) findViewById(R.id.addEventCatList);
        //TODO: Populate list with available categories
    }

    // Called when confirm button is clicked to save the event
    public void onConfirm(View view){
        // Find all fields
        EditText nameText = (EditText) findViewById(R.id.addEventNameText);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        ListView categoryView = (ListView) findViewById(R.id.addEventCatList);

        // Build event object
        String name = nameText.getText().toString(); // name
        if(name.isEmpty()){
            //TODO: Alert user that the event must be named
            System.out.println("DEBUG: Event left unnamed");
            finish(); // Temporary debugging
        }
        // Not sure if this works; needs testing and type research
        Date dueDate = new Date(calendarView.getDate()); // dueDate
        // Current time
        java.util.Date cur = new java.util.Date();
        Date currentDate = new Date(cur.getTime()); // currentDate
        if(dueDate.before(currentDate)){
            //TODO: Alert user that the event must be in the future
            // (Maybe don't have to enforce this)
            System.out.println("DEBUG: Selected date before current date...");
            finish();
        }
        // TODO: Get selected category from listview
        Category category = new Category("Test", "#FFFF00"); // Temporary category

        Event e = new Event(name, dueDate, currentDate, category);
        e.saveEvent(e, this);


    }

    public void setDate(View view){

    }

    public void setTime(View view){

    }

}
