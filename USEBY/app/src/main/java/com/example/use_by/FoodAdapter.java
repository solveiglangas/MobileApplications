package com.example.use_by;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodAdapter {
    public static final String DATABASE_NAME = "foodDB";
    public static final String DATABASE_TABLE = "foodList";
    public static final int DATABASE_VERSION = 2;
    public static final String KEY_ID = "id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String QUANTITY = "quantity";
    public static final String LOCATION = "location";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + DATABASE_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL, " + QUANTITY +" INTEGER NOT NULL, " + LOCATION+" TEXT NOT NULL);";

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private final Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
    public FoodAdapter(Context context){
        this.context = context;
    }

    public FoodAdapter open() throws SQLException{
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        return this;
    }
    public void close(){
        helper.close();
    }
    public Cursor fetchAllFood(){
        return db.query(DATABASE_TABLE, new String[]{KEY_ID, NAME, DATE, QUANTITY, LOCATION}, null, null, null, null, null);
    }
}
