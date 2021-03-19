package com.example.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "teams.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "TEAM_TABLE";
    public static final String COLUMN_TEAM_NAME = "TEAM_NAME";
    public static final String COLUMN_TEAM_SINCE = "TEAM_SINCE";
    public static final String COLUMN_ID = "ID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //this is called the first time a database is accessed. There should be code here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TEAM_NAME + " TEXT, " + COLUMN_TEAM_SINCE + " INT)";

        db.execSQL(createTableStatement);
    }

    //this is called if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addOne (TeamModel teamModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TEAM_NAME, teamModel.getTeamName());
        cv.put(COLUMN_TEAM_SINCE, teamModel.getYearFounded());

        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<TeamModel> getAllTeams(){

        List<TeamModel> returnList = new ArrayList<>();

        String queryStr = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryStr, null);

        if(cursor.moveToFirst()) {
            do {
                int teamID = cursor.getInt(0);
                String teamName = cursor.getString(1);
                int yearFounded = cursor.getInt(2);

                TeamModel newTeam = new TeamModel(teamID, teamName, yearFounded);
                returnList.add(newTeam);

            } while (cursor.moveToNext());

        }
        else {
            //failed, do nothing
        }

        //close both cursor and db
        cursor.close();
        db.close();

        return returnList;
    }

    //delete method
    public boolean deleteTeam(TeamModel teamModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryStr = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + teamModel.getId();

        Cursor cursor = db.rawQuery(queryStr, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //update method
    public void update(String id, String teamName, String teamYear){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TEAM_NAME, teamName);
        cv.put(COLUMN_TEAM_SINCE, teamYear);

        long result = db.update(TABLE_NAME, cv, "ID=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
}
