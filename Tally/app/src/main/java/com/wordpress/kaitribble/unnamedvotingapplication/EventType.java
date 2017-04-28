package com.wordpress.kaitribble.unnamedvotingapplication;

import java.util.List;

/**
 * Created by Kai on 4/24/2017.
 */
public class EventType {
    String eventTypeTitle;
    List<Event> eventList;
    int iconId;

    // Setters
    public void setEventTypeTitle(String eventType){
        this.eventTypeTitle = eventType;
    }

    public void setEventList(List<Event> eventList){
        this.eventList = eventList;
    }

    // Getters

    public String getEventTypeTitle() {
        return eventTypeTitle;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
