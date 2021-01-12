package com.example.conversationsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Tables_database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "storage.db";
    public static final String Table1 = "Conversation";
    public static final String T1Col1 = "Id";
    public static final String T1Col2 = "Name";
    public static final String Table2 = "Message";
    public static final String T2Col1 = "Msg_Id";
    public static final String T2Col2 = "Pname";
    public static final String T2Col3 = "msg_type";
    public static final String T2Col4 = "message";
    public static final String T2Col5 = "time";
    public static final String T2Col6 = "Status";

    public Tables_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE Conversation ( Id INTEGER PRIMARY KEY , " + " Name TEXT)";
        String sql1 = "CREATE TABLE Message ( Msg_Id INTEGER , " + " Pname TEXT, " + " msg_type TEXT ," + " message TEXT ," + " time TEXT ," + " Status TEXT)";
        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        db.execSQL("DROP TABLE IF EXISTS Conversation");
        db.execSQL("DROP TABLE IF EXISTS Message");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertConversation(int id, String convo_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1Col1, id);
        values.put(T1Col2, convo_name);

        long query = db.insertWithOnConflict(Table1, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        if (query == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean insertMessage(int mID, String name, String type, String msg, String timestamp, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T2Col1, mID);
        contentValues.put(T2Col2,name);
        contentValues.put(T2Col3, type);
        contentValues.put(T2Col4, msg);
        contentValues.put(T2Col5, timestamp);
        contentValues.put(T2Col6, status);

        long result = db.insertWithOnConflict(Table2, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);


        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor fetchData(int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Message where Msg_Id = " + user_id, null);
        return cursor;
    }

    public Cursor getDraft(int UserID, String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("User ID = " + UserID + " and Status = " + st);
        Cursor cursor = db.rawQuery("select * from Message where Msg_Id = " + UserID + " AND Status = ?", new String[]{st}, null);
        return cursor;
    }


}
