package com.norbiupdate1.pokesmile.norbiupdate1;

/**
 * Created by enorsza on 2015.02.02..
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_UPDATE1CODES = "update1codes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOODNAME = "foodname";
    public static final String COLUMN_UPDATE1CODE = "update1code";
    public static final String COLUMN_CH = "ch";
    public static final String COLUMN_GI = "gi";
    public static final String COLUMN_CATEGORIE = "categorie";
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_UPDATE1CODES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_FOODNAME + " text not null, "
            + COLUMN_UPDATE1CODE + " int not null, " + COLUMN_CH
            + " real, " + COLUMN_GI + " int, " + COLUMN_CATEGORIE + " text);";
    private static final String DATABASE_PATH = "/data/data/com.norbiupdate1.pokesmile.norbiupdate1/databases/";
    private static final String DATABASE_NAME = "update1codes.sqlite";
    private static final int DATABASE_VERSION = 1;
    private final Context myContext;
    private SQLiteDatabase db;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase daba, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        daba.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE1CODES);
        onCreate(daba);
    }

    public void createDatabase() {
        boolean dbExists = DBExists();

        if (!dbExists) {
//            this.getWritableDatabase();
            copyDBFromDatabase();
        } else {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            file.delete();
//            this.getWritableDatabase();
            copyDBFromDatabase();
        }
    }

    @Override
    public synchronized void close() {
        if (db != null) db.close();
        super.close();
    }

    private boolean DBExists() {
        db = null;

        try {
            String databasePath = DATABASE_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        } catch (SQLiteException e) {
            Log.e("SQLiteHelper", "database not found");
        }

        return db != null;
    }

    private void copyDBFromDatabase() {

        InputStream inputStream;
        OutputStream outStream;

        try {
            inputStream = myContext.getAssets().open(DATABASE_NAME);
            String dbFilePath = DATABASE_PATH + DATABASE_NAME;
            outStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            outStream.flush();
            outStream.close();
            inputStream.close();
        } catch (IOException e) {
            throw new Error("Problem copying database from resource file.");
        }
    }
}