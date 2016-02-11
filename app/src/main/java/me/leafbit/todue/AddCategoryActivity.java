package me.leafbit.todue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Adam on 2016-02-08.
 */
public class AddCategoryActivity extends AppCompatActivity {

    final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    String hexColor = "";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);



    }

    // Called when the confirm button is clicked
    public void onConfirm(View view){
        EditText nameText = (EditText) findViewById(R.id.addCategoryName);
        EditText hexText = (EditText) findViewById(R.id.addCatHexColor);
        hexColor = hexText.getText().toString();

        Category c = new Category(nameText.toString(), hexColor);
        Context ctx = getApplicationContext();
        long success = c.saveCategory(c, ctx);
        System.out.println("DEBUG: new row id = " + success);

        finish();

    }

    // Color selection dialog
    public void selectColor(View view) {

        final ColorPicker cp = new ColorPicker(AddCategoryActivity.this);

        cp.show();

        Button okColor = (Button)cp.findViewById(R.id.okColorButton);

        okColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.addCatHexColor);

                int selectedColorR = cp.getRed();
                int selectedColorG = cp.getGreen();
                int selectedColorB = cp.getBlue();

                String r, g, b;
                r = Integer.toHexString(selectedColorR);
                g = Integer.toHexString(selectedColorG);
                b = Integer.toHexString(selectedColorB);
                // Since Integer.toHexString() prunes to single 0 on zero
                if(selectedColorR == 0) r = "00";
                if(selectedColorG == 0) g = "00";
                if(selectedColorB == 0) b = "00";
                // Build output string
                hexColor = "#" + r + g + b;

                // Put the hex color in the text box
                editText.setText(hexColor);

                cp.dismiss();

            }
        });

    }
}
