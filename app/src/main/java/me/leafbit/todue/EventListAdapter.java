package me.leafbit.todue;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adam on 2016-02-12.
 */
public class EventListAdapter extends ArrayAdapter<Event> {

    public EventListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public EventListAdapter(Context context, int resource, List<Event> events){
        super(context, resource, events);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_event_listview, null);
        }

        Event e = getItem(position);
        if(e != null){
            TextView tv = (TextView) v.findViewById(R.id.eventListViewText);
            tv.setText(e.name);

            try {
                Category c = Category.loadCategoryByName(e.category, getContext());
                int color = Color.parseColor(c.hexColor);
                tv.setBackgroundColor(color);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("DEBUG: Category not found with name " + e.category);
            }
            TextView tc = (TextView) v.findViewById(R.id.textClock);
            tc.setText(e.dueDate);

        }
        return v;
    }

    public void refresh(){

    }


}
