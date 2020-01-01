package com.iqbal.eyetesting.ui.Database.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iqbal.eyetesting.ui.Database.DatabaseContract;
import com.iqbal.eyetesting.ui.Database.DatabaseHelper;

import static com.iqbal.eyetesting.ui.Database.DatabaseContract.TABLE_TEST;


public class TestHelper {
    private static final String DATABASE_TABLE = TABLE_TEST;
    private static DatabaseHelper dataBaseHelper;
    private static TestHelper INSTANCE;

    private static SQLiteDatabase database;

    public TestHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static TestHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.TestColumns._ID + " DESC");
    }

    public Cursor queryDataRandom14() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                "RANDOM()"
                , String.valueOf(14));

    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null
                , DatabaseContract.TestColumns._ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryByKategori(String kategori) {
        return database.query(DATABASE_TABLE, null
                , DatabaseContract.TestColumns.KATEGORI + " = ?"
                , new String[]{kategori}
                , null
                , null
                , null
                , null);
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.TestColumns._ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.TestColumns._ID + " = ?", new String[]{id});
    }
}
