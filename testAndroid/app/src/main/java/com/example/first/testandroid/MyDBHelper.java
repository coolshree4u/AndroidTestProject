package com.example.first.testandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "mysqldb.db";
    public static final int VERSION = 1;


    private static final String TABLE_NAME = "employees";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS = "address";
    public static final String SALARY = "salary";

    public MyDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    SQLiteDatabase mysqlDB;

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "create table " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST_NAME + " TEXT NOT NULL," + LAST_NAME + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + SALARY + " REAL NOT NULL )";
        db.execSQL(queryTable);
    }

    public void openDb() {
        mysqlDB = getWritableDatabase();
    }

    public void closeDB() {
        if (mysqlDB != null && mysqlDB.isOpen()) {
            mysqlDB.close();
        }
    }

    public long insert(int id, String fName, String lName, String address, Double salary) {
        ContentValues values = new ContentValues();
        if (id != -1) {
            values.put(ID, id);
        }

        values.put(FIRST_NAME, fName);
        values.put(LAST_NAME, lName);
        values.put(ADDRESS, address);
        values.put(SALARY, salary);

        return mysqlDB.insert(TABLE_NAME, null, values);

    }

    public long update(int id, String fName, String lName, String address, Double salary) {
        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, fName);
        values.put(LAST_NAME, lName);
        values.put(ADDRESS, address);
        values.put(SALARY, salary);

        String where = ID + " = " + id;
        return mysqlDB.update(TABLE_NAME, values, where, null);

    }


    public long delete(int id) {
        String where = ID + " = " + id;
        return mysqlDB.delete(TABLE_NAME, where, null);
    }


    public Cursor getAllRecords() {


        String selectQuery = "Select * from " + TABLE_NAME;
        return mysqlDB.rawQuery(selectQuery, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
