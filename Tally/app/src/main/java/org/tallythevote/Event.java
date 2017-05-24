package org.tallythevote;

import java.util.Calendar;

/**
 * Created by Kai on 4/24/2017.
 */
public class Event {
    int eventID, zipCode;
    String eventName, eventDescription, address;
    Politician politician;
    Calendar date;
    String location;

    // Constructor
    Event(int eventID, int zipCode, String eventName, String eventDescription, Calendar eventDate, String address){
        this.eventID = eventID;
        this.zipCode = zipCode;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.date = eventDate;
        this.address = address;
    }

    // Setters


    public void setAddress(String address) {
        this.address = address;
    }

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

    public void setDate(Calendar date){
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

    public Calendar getDate() {
        return date;
    }
}
