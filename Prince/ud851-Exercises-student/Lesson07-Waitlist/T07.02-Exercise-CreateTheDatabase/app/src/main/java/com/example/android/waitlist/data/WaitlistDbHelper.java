package com.example.android.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Complete (1) extend the SQLiteOpenHelper class
public class WaitlistDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "waitlist.db";

    public WaitlistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE "+WaitlistContract.WaitlistEntry.TABLE_NAME
                +"("+WaitlistContract.WaitlistEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME+" TEXT, "
                +WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE+" TEXT, "
                +WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP+" TEXT);";

        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String SQL_DROP_WAITLIST_TABLE = "DROP TABLE IF EXISTS "+WaitlistContract.WaitlistEntry.TABLE_NAME;
        db.execSQL(SQL_DROP_WAITLIST_TABLE);
        onCreate(db);
    }

    // Completed (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"

    // Completed (3) Create a static final int called DATABASE_VERSION and set it to 1

    // Completed (4) Create a Constructor that takes a context and calls the parent constructor

    // Completed (5) Override the onCreate method

        // Completed (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table

        // Completed (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE

    // Completed (8) Override the onUpgrade method

        // Completed (9) Inside, execute a drop table query, and then call onCreate to re-create it

}