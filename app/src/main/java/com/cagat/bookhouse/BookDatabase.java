package com.cagat.bookhouse;

/**
 * Created by cagat on 2.01.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class BookDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BookDatabase"; //Database name.

    private static final String TABLE_NAME = "book_list";
    private static String BOOK_NAME = "book_name";
    private static String BOOK_ID = "id";
    private static String BOOK_AUTHOR = "book_author";
    private static String BOOK_PRINT_YEAR = "book_print_year";
    private static String BOOK_TOUGHTS = "book_toughts";

    public BookDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  // Creating database. Method comes automatically.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BOOK_NAME + " VARCHAR,"
                + BOOK_AUTHOR + " VARCHAR,"
                + BOOK_PRINT_YEAR + " VARCHAR,"
                + BOOK_TOUGHTS + " VARCHAR" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void bookAdd(String book_name, String book_author, String book_print_year, String book_toughts) {
        //For adding book to database, creating bookAdd method.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book_name);
        values.put(BOOK_AUTHOR, book_author);
        values.put(BOOK_PRINT_YEAR, book_print_year);
        values.put(BOOK_TOUGHTS, book_toughts);

        db.insert(TABLE_NAME, null, values);
        db.close(); //Closing database connection.
    }

    public HashMap<String, String> bookDetail(int id) {
        HashMap<String, String> book = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            book.put(BOOK_NAME, cursor.getString(1));
            book.put(BOOK_AUTHOR, cursor.getString(2));
            book.put(BOOK_PRINT_YEAR, cursor.getString(3));
            book.put(BOOK_TOUGHTS, cursor.getString(4));
        }
        cursor.close();
        db.close();
        return book;
    }

    public ArrayList<HashMap<String, String>> books() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> bookList = new ArrayList<HashMap<String, String>>();
        // looking through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                bookList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return bookList
        return bookList;
    }

    public void resetTables() {
        //Not using in app. This area deletes all data and table.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}