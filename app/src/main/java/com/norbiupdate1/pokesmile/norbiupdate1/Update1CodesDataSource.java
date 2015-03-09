package com.norbiupdate1.pokesmile.norbiupdate1;

/**
 * Created by enorsza on 2015.02.02..
 */

import android.content.ContentValues;
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
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FOODNAME,
            MySQLiteHelper.COLUMN_UPDATE1CODE,
            MySQLiteHelper.COLUMN_CH,
            MySQLiteHelper.COLUMN_GI,
            MySQLiteHelper.COLUMN_CATEGORIE };

    public Update1CodesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createPreFilledDatabase(){
        dbHelper.createDatabase();
    }

    public Update1Codes createUpdate1Codes(String foodname, int update1code, double cH, int gI, String categorie) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_FOODNAME, foodname);
        values.put(MySQLiteHelper.COLUMN_UPDATE1CODE, update1code);
        values.put(MySQLiteHelper.COLUMN_CH, cH);
        values.put(MySQLiteHelper.COLUMN_GI, gI);
        values.put(MySQLiteHelper.COLUMN_CATEGORIE, categorie);
        long insertId = database.insert(MySQLiteHelper.TABLE_UPDATE1CODES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_UPDATE1CODES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Update1Codes newUpdate1Code = cursorToUpdate1Code(cursor);
        cursor.close();
        return newUpdate1Code;
    }

    public void deleteUpdate1Code(Update1Codes Update1Code) {
        long id = Update1Code.getId();
        System.out.println("Update1Code deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_UPDATE1CODES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Update1Codes> getAllUpdate1Codes() {
        List<Update1Codes> Update1Codes = new ArrayList<Update1Codes>();

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
        Update1Code.setId(cursor.getLong(0));
        Update1Code.setFoodName(cursor.getString(1));
        Update1Code.setUpdate1Code(cursor.getInt(2));
        Update1Code.setcH(cursor.getDouble(3));
        Update1Code.setgI(cursor.getInt(4));
        Update1Code.setCategorie(cursor.getString(5));
        return Update1Code;
    }
}
