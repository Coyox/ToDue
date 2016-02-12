package me.leafbit.todue;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity {
    String selectedCategory;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //load all categories and display them in the list
        ArrayList<Category> categories = Category.loadAllCategories(this);
        //TODO: Populate list with available categories
        //ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, categories);
        final ListView categoryView = (ListView) findViewById(R.id.addEventCatList);
        //categoryView.setAdapter(arrayAdapter);
        CategoryListAdapter customAdapter = new CategoryListAdapter(this, R.layout.activity_listview, categories);
        //categoryView.setEnabled(true);
        categoryView.setAdapter(customAdapter);


        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) categoryView.getItemAtPosition(position);
                selectedCategory = category.id;
                System.out.println("DEBUG: Selected the " + selectedCategory + " category");

                // Make selected item text white
                TextView tv = (TextView) view.findViewById(R.id.label);
                tv.setTextColor(Color.WHITE);
                categoryView.setSelection(position);
                selectedPosition = position;
            }
        });


    }

    // Called when confirm button is clicked to save the event
    public void onConfirm(View view){
        // Find all fields
        EditText nameText = (EditText) findViewById(R.id.addEventNameText);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        //ListView categoryView = (ListView) findViewById(R.id.addEventCatList);

        // Build event object
        String name = nameText.getText().toString(); // name
        if(name.isEmpty()){
            Toast.makeText(this, "Please name the event", Toast.LENGTH_SHORT);
            System.out.println("DEBUG: Event left unnamed");
            finish(); // Temporary debugging
        }
        // Not sure if this works; needs testing and type research
        Date dueDate = new Date(calendarView.getDate()); // dueDate
        // Current time
        java.util.Date cur = new java.util.Date();
        Date currentDate = new Date(cur.getTime()); // currentDate
        if(dueDate.before(currentDate)){
            Toast.makeText(this, "Event is due in the past!", Toast.LENGTH_LONG);
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
