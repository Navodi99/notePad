package com.example.notepadapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notepadapplication.noteModel.Note;

import java.util.ArrayList;
import java.util.List;

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

    public List<Note> getNote(){
        List<Note> notes=new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();
        String sql="SELECT * FROM notes";

        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                Note note=new Note();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setDate(cursor.getString(2));
                note.setBody(cursor.getString(3));

                notes.add(note);
            }
            while (cursor.moveToNext());
        }
        return notes;
    }
    public Note getSingleNote(int id){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.query("notes",new String[]{"id","title","date","body"},"id=?",new String[]{String.valueOf(id)},null,null,null );

        Note note;
        if(cursor != null){
            cursor.moveToFirst();
            note=new Note(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            return note;
        }
        return null;
    }
    public void deleteNote(int id){
        SQLiteDatabase database=getReadableDatabase();
        database.delete("notes","id=?",new String[]{String.valueOf(id)});
        database.close();
    }
    public  int updateNote(Note note){
        SQLiteDatabase database=getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("title",note.getTitle());
        contentValues.put("date",note.getDate());
        contentValues.put("body",note.getBody());

        int status=database.update("notes",contentValues,"id=?",new String[]{String.valueOf(note.getId())});
        database.close();
        return status;
    }
    public List<Note> search (String query) {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM notes WHERE title LIKE ?";

        Cursor cursor = database.rawQuery(sql, new String[]{"%" + query + "%"});
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setDate(cursor.getString(2));
                note.setBody(cursor.getString(3));

                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }



}
