package io.github.karan.kotlin_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "EMPLOYEES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";

    // Database Information
    static final String DB_NAME = "myEmployees.DB";

    // database version
    static final int DB_VERSION = 1;

    private SQLiteDatabase database;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " +  ADDRESS + " CHAR(50));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(String name, String address)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllEmployees()
    {
        String[] projection = {
                _ID, NAME, ADDRESS
        };

        Cursor cursor = database.query(TABLE_NAME, projection, null, null, null, null, null);

        return cursor;
    }

    public int update(long _id, String name, String address) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);

        int count = database.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        return count;
    }

    public void delete(long _id)
    {
        database.delete(TABLE_NAME, _ID + "=" + _id, null);
    }

    public Cursor getNames(String namePassedIn)
    {
        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                _ID, NAME, ADDRESS
        };

        // Filter results WHERE "name" = passed in parameter
        String selection = NAME + " = ?";
        String[] selectionArgs = { namePassedIn};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = NAME + " DESC";

        Cursor cursor = database.query(
                TABLE_NAME,                     // The table to query
                projection,                     // The columns to return
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                   // don't group the rows
                null,                    // don't filter by row groups
                sortOrder                      // The sort order
        );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}

