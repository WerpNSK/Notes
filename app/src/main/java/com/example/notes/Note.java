package com.example.notes;

public class Note
{
    private int id;
    private String header;
    private String body;
    private String date;

    public Note(String header, String body, String date)
    {
        this.header = header;
        this.body = body;
        this.date = date;
    }

    public Note(int id, String header, String body, String date)
    {
        this.id = id;
        this.header = header;
        this.body = body;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public String getHeader()
    {
        return header;
    }

    public String getBody()
    {
        return body;
    }

    public String getDate()
    {
        return date;
    }

}
