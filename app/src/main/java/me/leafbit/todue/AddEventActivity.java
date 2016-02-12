package me.leafbit.todue;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

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

        // Get current date and time then format

        Date todayDate = new Date();
        String currentDate = Event.DATE_FORMAT.format(todayDate);
        System.out.println("DEBUG: Current Date is " + currentDate);
        //TODO: Get selected date and time then format
        //Date dueDate = new Date(calendarView.getDate());
        //String selectedDate = Event.DATE_FORMAT.format(dueDate);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; //Starts from 0
        int year = datePicker.getYear();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        System.out.println("DEBUG: MONTH IS   " + month);

        String selectedDate = parseTime(day, month, year, hour, minute);
        System.out.println("DEBUG: Selected Date is " + selectedDate);

        //If nothing went wrong save to database
        if(clearance){
            //Event e = new Event(name, dueDate, currentDate, selectedCategory);
            //e.saveEvent(e, this);
        }

    }

    // Returns correctly formatted date/time string (hour and minute input in 24 hour format)
    private String parseTime(int day, int month, int year, int hour, int minute){
        // Convert 24 hour time to 12 hour with am/pm
        String twentyFourHourTime = hour + ":" + minute;
        //String twelveHourTime;
        SimpleDateFormat twentyfourHour = new SimpleDateFormat("HH:mm");
        SimpleDateFormat twelveHour = new SimpleDateFormat("hh:mm a");
        try {
            Date d24 = twentyfourHour.parse(twentyFourHourTime);
            String twelveHourTime = twelveHour.format(d24);
            //System.out.println("DEBUG: Twelve hour format : " + twelveHourTime);
            String sMonth = String.format("%02d",month);
            String selectedDate = year + "-" + sMonth + "-" + day
                    + " " + twelveHourTime;
            return selectedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("DEBUG: Date parse error.");
        }
        return "";
    }

    public void setDate(View view){

    }

    public void setTime(View view){

    }

}
