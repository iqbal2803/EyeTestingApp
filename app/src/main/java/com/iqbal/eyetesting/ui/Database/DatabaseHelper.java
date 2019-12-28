package com.iqbal.eyetesting.ui.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_eyetesting_app";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TEST = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s BLOB NOT NULL)",
            DatabaseContract.TABLE_TEST,
            DatabaseContract.TestColumns._ID,
            DatabaseContract.TestColumns.ANGKA,
            DatabaseContract.TestColumns.KATEGORI,
            DatabaseContract.TestColumns.PHOTO
    );

    private static final String SQL_CREATE_TABLE_USER = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_TEST,
            DatabaseContract.TestColumns._ID,
            DatabaseContract.UserColumns.NAMA,
            DatabaseContract.UserColumns.UMUR,
            DatabaseContract.UserColumns.JENIS_KELAMIN,
            DatabaseContract.UserColumns.NILAI
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TEST);
        db.execSQL(SQL_CREATE_TABLE_USER);
        
        inputdatatest(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_TEST);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_USER);
        onCreate(db);
    }

    public void inputdatatest(SQLiteDatabase db) {
        db.execSQL("insert into tb_test values(Null,'12','mudah','plate1.png')");
        db.execSQL("insert into tb_test values(Null,'8','mudah','plate2.png')");
        db.execSQL("insert into tb_test values(Null,'6','mudah','plate3.png')");
        db.execSQL("insert into tb_test values(Null,'29','mudah','plate4.png')");
        db.execSQL("insert into tb_test values(Null,'57','mudah','plate5.png')");
        db.execSQL("insert into tb_test values(Null,'5','mudah','plate6.png')");
        db.execSQL("insert into tb_test values(Null,'3','mudah','plate7.png')");
        db.execSQL("insert into tb_test values(Null,'15','mudah','plate8.png')");
        db.execSQL("insert into tb_test values(Null,'74','mudah','plate9.png')");
        db.execSQL("insert into tb_test values(Null,'2','mudah','plate10.png')");
        db.execSQL("insert into tb_test values(Null,'6','mudah','plate11.png')");
        db.execSQL("insert into tb_test values(Null,'97','mudah','plate12.png')");
        db.execSQL("insert into tb_test values(Null,'45','mudah','plate13.png')");
        db.execSQL("insert into tb_test values(Null,'5','mudah','plate14.png')");
        db.execSQL("insert into tb_test values(Null,'7','mudah','plate15.png')");
        db.execSQL("insert into tb_test values(Null,'16','mudah','plate16.png')");
        db.execSQL("insert into tb_test values(Null,'73','mudah','plate17.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate18.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate19.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate20.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate21.png')");
        db.execSQL("insert into tb_test values(Null,'26','mudah','plate22.png')");
        db.execSQL("insert into tb_test values(Null,'42','mudah','plate23.png')");
        db.execSQL("insert into tb_test values(Null,'35','mudah','plate24.png')");
        db.execSQL("insert into tb_test values(Null,'96','mudah','plate25.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate26.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate27.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate28.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate29.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate30.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate31.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate32.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate33.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate34.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate35.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate36.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate37.png')");
        db.execSQL("insert into tb_test values(Null,'0','mudah','plate38.png')");
    }

    }
