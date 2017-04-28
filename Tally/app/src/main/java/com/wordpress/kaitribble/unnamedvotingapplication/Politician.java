package com.wordpress.kaitribble.unnamedvotingapplication;

/**
 * Created by Kai on 4/24/2017.
 */
public class Politician {
    private int politicianId;
    private String politicianName, politicianPosition, politicianDescription;
    private boolean followMe;

    Politician(int politicianId, String politicianName, String politicianPosition, String politicianDescription){
        this.politicianId = politicianId;
        this.politicianName = politicianName;
        this.politicianPosition = politicianPosition;
        this.politicianDescription = politicianDescription;
    }

    public void setPoliticianId(int politicianId) {
        this.politicianId = politicianId;
    }
    public void setPoliticianName(String politicianName) { this.politicianName = politicianName; }
    public void setPoliticianPosition(String politicianPosition) { this.politicianPosition = politicianPosition; }
    public void setPoliticianDescription(String politicianDescription) {this.politicianDescription = politicianDescription; }
    public void setFollowMe(boolean followMe) {
        this.followMe = followMe;
    }

    public int getPoliticianId() {
        return politicianId;
    }
    public String getPoliticianName(){
        return politicianName;
    }

    public String getPoliticianPosition() {
        return politicianPosition;
    }

    public boolean isFollowMe() {
        return followMe;
    }

    public String getPoliticianDescription() {
        return politicianDescription;

    }

}
