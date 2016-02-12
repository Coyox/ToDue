package me.leafbit.todue;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity {
    String selectedCategory = "";
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //load all categories and display them in the list
        ArrayList<Category> categories = Category.loadAllCategories(this);
        final ListView categoryView = (ListView) findViewById(R.id.addEventCatList);
        CategoryListAdapter customAdapter = new CategoryListAdapter(this, R.layout.activity_category_listview, categories);
        categoryView.setAdapter(customAdapter);


        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Fix bug where previous clicked row remains white
                Category category = (Category) categoryView.getItemAtPosition(position);
                selectedCategory = category.id;
                System.out.println("DEBUG: Selected the " + selectedCategory + " category");

                // Make selected item text white
                TextView tv = (TextView) view.findViewById(R.id.label);
                tv.setTextColor(Color.WHITE);
                //categoryView.setSelection(position);
                selectedPosition = position;
            }
        });


    }

    // Called when confirm button is clicked to save the event
    public void onConfirm(View view){
        // Find all fields
        boolean clearance = true; // Set to false if form incomplete or misformatted
        EditText nameText = (EditText) findViewById(R.id.addEventNameText);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        //ListView categoryView = (ListView) findViewById(R.id.addEventCatList);

        // Build event object
        String name = nameText.getText().toString(); // name
        if(name.isEmpty()){
            Toast.makeText(this, "Please name the event", Toast.LENGTH_SHORT).show();
            System.out.println("DEBUG: Event left unnamed");
            clearance = false;
        }
        if(selectedCategory.equals("")){
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            System.out.println("DEBUG: No Category selected");
            clearance = false;
        }

        // Not sure if this works; needs testing and type research
        Date dueDate = new Date(calendarView.getDate());


        // Current time
        Date currentDate = new Date(); // currentDate

        if(dueDate.before(currentDate)){
            Toast.makeText(this, "Event is due in the past!", Toast.LENGTH_LONG).show();
            System.out.println("Due date: " + dueDate.getTime() + " Current date: " + currentDate.getTime());
            // (Maybe don't have to enforce this)
            System.out.println("DEBUG: Selected date before current date...");
            //clearance = false; // Letting it slide for now... Always seems to be before needs testing
        }

        //If nothing went wrong save to database
        if(clearance){
            //Event e = new Event(name, dueDate, currentDate, selectedCategory);
            //e.saveEvent(e, this);
        }

    }

    public void setDate(View view){

    }

    public void setTime(View view){

    }

}
