package com.example.notes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseConnection
{
    SQLiteDatabase db;
    ArrayList<Note> notes;

    public DatabaseConnection(SQLiteDatabase db)
    {
        notes = new ArrayList<Note>();
        this.db = db;
        String query = "CREATE TABLE IF NOT EXISTS notestable" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "header TEXT," +
                "body TEXT," +
                "date TEXT)";
        db.execSQL(query);
    }

    public boolean InsertNote(Note note)
    {
        try
        {
            String query = "INSERT INTO notestable (header, body, date)" +
                    "VALUES(" +
                    "'" + note.getHeader() + "'," +
                    "'" + note.getBody() + "'," +
                    "'" + note.getDate() + "')";
            db.execSQL(query);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public ArrayList<Note> getNotes()
    {
        notes.clear();
        String query = "SELECT * FROM notestable";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String header = cursor.getString(1);
            String body = cursor.getString(2);
            String date = cursor.getString(3);
            notes.add(new Note(id, header, body, date));
        }
        cursor.close();
        return notes;
    }
/*
    public boolean DeleteNote(int id)
    {
        try
        {
            String query = "DELETE FROM notestable WHERE id=" + id;
            db.execSQL(query);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
*/
    public boolean DeleteNote(Note note)
    {
        try
        {
            String query = "DELETE FROM notestable " + "WHERE id="+String.valueOf(note.getId());
            db.execSQL(query);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

}
