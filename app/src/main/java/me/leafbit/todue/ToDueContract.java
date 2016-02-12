package me.leafbit.todue;

import android.provider.BaseColumns;

/**
 * Created by Adam on 2016-02-10.
 */
public final class ToDueContract {

    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "c_name";
        public static final String COLUMN_NAME_COLOR = "c_color";
    }

    public static abstract class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_EVENT_NAME = "e_name";
        public static final String COLUMN_NAME_DUE = "e_dueDate";
        public static final String COLUMN_NAME_START = "e_startDate";
    }

    private static final String TEXT_TYPE = " varchar(120)";
    private static final String NAME_TYPE = " varchar(60)";
    private static final String COLOR_TYPE = " char(7)";
    private static final String DATE_TYPE = " char(19)"; // "yyyy-MM-dd hh:mm aa"
    private static final String COMMA = ",";
    public static final String SQL_CREATE_CAT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CategoryEntry.TABLE_NAME + " ("
            + CategoryEntry.COLUMN_NAME_NAME + NAME_TYPE + " PRIMARY KEY,"
            + CategoryEntry.COLUMN_NAME_COLOR + COLOR_TYPE + ");";

    public static final String SQL_CREATE_EVENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + EventEntry.TABLE_NAME + " ("
            + EventEntry.COLUMN_NAME_EVENT_NAME + TEXT_TYPE + " PRIMARY KEY,"
            + EventEntry.COLUMN_NAME_DUE + DATE_TYPE + COMMA
            + EventEntry.COLUMN_NAME_START + DATE_TYPE + COMMA
            + CategoryEntry.COLUMN_NAME_NAME + NAME_TYPE + COMMA
            + "FOREIGN KEY(" + CategoryEntry.COLUMN_NAME_NAME + ") REFERENCES "
            + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_NAME + "));";

    public static final String SQL_DELETE_ALL_CATEGORY =
            "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME;
    public static final String SQL_DELETE_ALL_EVENT =
            "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;
}
