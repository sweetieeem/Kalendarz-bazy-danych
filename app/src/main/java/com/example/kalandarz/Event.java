package com.example.kalandarz;

public class Event {
    int _id;
    String _name;
    String _date;
    public Event(){   }
    public Event(int id, String name, String _date){
        this._id = id;
        this._name = name;
        this._date = _date;
    }

    public Event(String name, String _date){
        this._name = name;
        this._date = _date;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getDate(){
        return this._date;
    }

    public void setDate(String date){
        this._date = date;
    }
}

