package me.leafbit.todue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import me.leafbit.todue.ToDueContract;

/**
 * Created by Adam on 2016-02-10.
 */
public class ToDueDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; // Must increment if tables changed
    public static final String DATABASE_NAME = "ToDue.db";

    public ToDueDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ToDueContract.SQL_CREATE_CAT_TABLE);
        db.execSQL(ToDueContract.SQL_CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Handle an upgrade in a non-destructive way
        db.execSQL(ToDueContract.SQL_DELETE_ALL_CATEGORY);
        db.execSQL(ToDueContract.SQL_DELETE_ALL_EVENT);
        onCreate(db);

    }
}
