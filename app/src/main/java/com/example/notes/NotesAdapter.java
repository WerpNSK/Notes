package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NotesAdapter extends BaseAdapter
{
    ArrayList<Note> notes;
    LayoutInflater inflater;
    Context context;

    public NotesAdapter(Context context, ArrayList<Note> notes)
    {
        this.notes = notes;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return notes.size();
    }

    @Override
    public Object getItem(int i)
    {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = view;
        if (v == null)
        {
            v = inflater.inflate(R.layout.item, viewGroup, false);
        }
        TextView nameNote = v.findViewById(R.id.headerTV);
        TextView textNote = v.findViewById(R.id.bodyTV);
        TextView dateNote = v.findViewById(R.id.dateTV);

        nameNote.setText(notes.get(i).getHeader());
        textNote.setText(notes.get(i).getBody());
        dateNote.setText(notes.get(i).getDate());

        return v;
    }
}
