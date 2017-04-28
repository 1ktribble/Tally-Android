package com.wordpress.kaitribble.unnamedvotingapplication;

import java.util.Date;

/**
 * Created by Kai on 4/24/2017.
 */
public class Event {
    int eventID, zipCode;
    String eventName, eventDescription, address;
    Politician politician;
    Date date;

    // Constructor
    Event(int eventID, int zipCode, String eventName, String eventDescription){
        this.eventID = eventID;
        this.zipCode = zipCode;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    // Setters

    public void setEventId(int eventId){
        this.eventID = eventId;
    }

    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public void setEventDescription(String eventDescription){
        this.eventDescription = eventDescription;
    }

    public void seteventAddress(String eventAddress){
        this.address = eventAddress;
    }

    public void setPolitician(Politician politician){
        this.politician = politician;
    }

    public void setDate(Date date){
        this.date = date;
    }

    // Getters

    public int getEventID(){
        return eventID;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getAddress() {
        return address;
    }

    public Politician getPolitician() {
        return politician;
    }

    public Date getDate() {
        return date;
    }
}
