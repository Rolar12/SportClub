package com.android.sportclub.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SportClubDbHelper extends SQLiteOpenHelper {
    public SportClubDbHelper(Context context) {
        super(context, SportClubContract.DATABASE_NAME, null, SportClubContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + SportClubContract.MemberEntry.TABLE_NAME + "("
                + SportClubContract.MemberEntry._ID + " INTEGER PRIMARY KEY,"
                + SportClubContract.MemberEntry.COLUMN_FIRST_NAME + " TEXT,"
                + SportClubContract.MemberEntry.COLUMN_LAST_NAME + " TEXT,"
                + SportClubContract.MemberEntry.COLUMN_GENDER + " INTEGER NOT NULL,"
                + SportClubContract.MemberEntry.COLUMN_SPORT + " TEXT" + ")";
        db.execSQL(CREATE_MEMBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SportClubContract.MemberEntry.TABLE_NAME);
        onCreate(db);
    }
}
