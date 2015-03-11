package com.norbiupdate1.pokesmile.norbiupdate1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by enorsza on 2015.03.10..
 */
public class HtmlParser {

    private static final String TEXTFILE_NAME = "update1codes.html";
    private final Context myContext;
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public HtmlParser(Context myContext, MySQLiteHelper dbHelp) {
        this.myContext = myContext;
        this.dbHelper = dbHelp;
        this.database = dbHelper.getWritableDatabase();
    }

    public void copyDBFromResource() {

        ContentValues values = new ContentValues();
        InputStream inputStream;
        Double d;
//        int id = 1;
//        List<Update1Codes> values = new ArrayList<>();

        try {
            inputStream = myContext.getAssets().open(TEXTFILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder lastCategorie = new StringBuilder();

//            Update1Codes update1Code = new Update1Codes();

            int lineCounter = 0;
            while ((line = reader.readLine()) != null) {

                if ((line.length() >= 3) && (line.subSequence(0, 3).equals("<C>"))) {
                    if (lastCategorie.length() != 0)
                        lastCategorie.delete(0, lastCategorie.length());
                    lastCategorie.append(line.substring(3));
                    lineCounter = 0;
                } else if ((line.length() >= 3) && (line.subSequence(0, 3).equals("<U>"))) {
//                    update1Code.setUpdate1Code(Integer.valueOf(line.substring(3)));
                    values.put(MySQLiteHelper.COLUMN_UPDATE1CODE, Integer.valueOf(line.substring(3)));
                    lineCounter = 1;
                } else if (lineCounter == 1) {
//                    update1Code.setFoodName(line);
                    values.put(MySQLiteHelper.COLUMN_FOODNAME, line);
                    lineCounter++;
                } else if (lineCounter == 2) {
//                    update1Code.setcH(Double.valueOf(line));
                    d = Double.valueOf(line);
                    values.put(MySQLiteHelper.COLUMN_CH, d.intValue());
                    lineCounter++;
                } else if (lineCounter == 3) {
//                    update1Code.setgI(Integer.valueOf(line));
                    values.put(MySQLiteHelper.COLUMN_GI, Integer.valueOf(line));
                    values.put(MySQLiteHelper.COLUMN_CATEGORIE, lastCategorie.toString());
//                    update1Code.setCategorie(lastCategorie.toString());
//                    update1Code.setId(id);
//                    values.add(update1Code);
//                    id++;
                    database.insert(MySQLiteHelper.TABLE_UPDATE1CODES, null, values);
                    values.clear();
                    lineCounter = 0;
                }
            }
        } catch (IOException e) {
            throw new Error("Problem copying database from resource file.");
        }
//        return values;
    }
}
