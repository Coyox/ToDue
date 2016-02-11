package me.leafbit.todue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
    }

    public void deleteAll(View view){
        Context ctx = this;
        System.out.println("DEBUG: Dropping category table...");
        ToDueDbHelper dbHelper = new ToDueDbHelper(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(ToDueContract.SQL_DELETE_ALL_CATEGORY);
        System.out.println("DEBUG: Dropped category table.");

        //Rebuild table
        db.execSQL(ToDueContract.SQL_CREATE_CAT_TABLE);

        finish();
    }
}
