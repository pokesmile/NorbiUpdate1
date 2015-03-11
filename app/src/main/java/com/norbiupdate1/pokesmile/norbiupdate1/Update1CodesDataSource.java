package com.norbiupdate1.pokesmile.norbiupdate1;

/**
 * Created by enorsza on 2015.02.02..
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Update1CodesDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private HtmlParser parser;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FOODNAME,
            MySQLiteHelper.COLUMN_UPDATE1CODE,
            MySQLiteHelper.COLUMN_CH,
            MySQLiteHelper.COLUMN_GI,
            MySQLiteHelper.COLUMN_CATEGORIE };

    public Update1CodesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        parser = new HtmlParser(context, dbHelper);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createPreFilledDatabase(){
        dbHelper.createDatabase();
    }

    public void createUpdate1Codes() {
        parser.copyDBFromResource();
    }

    public void deleteUpdate1Code(Update1Codes Update1Code) {
        long id = Update1Code.getId();
        System.out.println("Update1Code deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_UPDATE1CODES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Update1Codes> getAllUpdate1Codes() {
        List<Update1Codes> Update1Codes = new ArrayList<>();

        Cursor cursor;
        cursor = database.query(MySQLiteHelper.TABLE_UPDATE1CODES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Update1Codes Update1Code = cursorToUpdate1Code(cursor);
            Update1Codes.add(Update1Code);
            cursor.moveToNext();
        }

        cursor.close();
        return Update1Codes;
    }

    private Update1Codes cursorToUpdate1Code(Cursor cursor) {
        Update1Codes Update1Code = new Update1Codes();
        Update1Code.setId(cursor.getInt(0));
        Update1Code.setFoodName(cursor.getString(1));
        Update1Code.setUpdate1Code(cursor.getInt(2));
        Update1Code.setcH(cursor.getInt(3));
        Update1Code.setgI(cursor.getInt(4));
        Update1Code.setCategorie(cursor.getString(5));
        return Update1Code;
    }
}
