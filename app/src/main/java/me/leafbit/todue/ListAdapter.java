package me.leafbit.todue;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adam on 2016-02-11.
 */
public class ListAdapter extends ArrayAdapter<Category> {
    // Source: http://stackoverflow.com/questions/8166497/custom-adapter-for-list-view

    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ListAdapter(Context context, int resource, List<Category> categories){
        super(context, resource, categories);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_listview, null);
        }

        Category c = getItem(position);

        if(c != null){
            TextView textView = (TextView) v.findViewById(R.id.label);
            //System.out.println("CATEGORY: " + c.id);
            textView.setText(c.id);
            int color = Color.parseColor(c.hexColor);
            textView.setBackgroundColor(color);
        }
        return v;
    }

}
