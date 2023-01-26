package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase db;
    DatabaseConnection connection;
    ArrayList<Note> notes;
    ListView listView;
    NotesAdapter adapter;
    int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        db = getBaseContext().openOrCreateDatabase("noteDB.db", MODE_PRIVATE, null);
        connection = new DatabaseConnection(db);

        notes = connection.getNotes();
        NotesAdapter adapter = new NotesAdapter(this, notes);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedIndex = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete note?");
                builder.setMessage(notes.get(i).getHeader());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        connection.DeleteNote(notes.get(selectedIndex));

                        notes = connection.getNotes();
                        NotesAdapter adapter = new NotesAdapter(MainActivity.this, notes);
                        listView.setAdapter(adapter);
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }
}