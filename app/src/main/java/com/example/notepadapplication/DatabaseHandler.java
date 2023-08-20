package com.example.notepadapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notepadapplication.noteModel.Note;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME="allNotes";
    private static final int VERSION=1;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql="CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,date TEXT,body TEXT);" ;
            sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql="DROP TABLE IF EXISTS note";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
    }
    public void saveNote(Note note){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("title",note.getTitle());
        contentValues.put("date",note.getDate());
        contentValues.put("body",note.getBody());

        sqLiteDatabase.insert("notes",null,contentValues);
        sqLiteDatabase.close();
    }
}
