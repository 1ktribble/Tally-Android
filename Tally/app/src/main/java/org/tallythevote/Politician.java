package org.tallythevote;

/**
 * Created by Kai on 4/24/2017.
 */
public class Politician {
    private int politicianId, politicianImage;
    private String politicianName, politicianPosition, politicianDescription, politicianRegion, politicianEmail,
            politicianPhone;
    private boolean followMe;

    public String getPoliticianPhone() {
        return politicianPhone;
    }

    public void setPoliticianPhone(String politicianPhone) {
        this.politicianPhone = politicianPhone;
    }

    public String getPoliticianEmail() {
        return politicianEmail;
    }

    public void setPoliticianEmail(String politicianEmail) {
        this.politicianEmail = politicianEmail;
    }

    Politician(int politicianId, String politicianName, String politicianPosition, String politicianRegion,
               String politicianPhone, String politicianEmail, String politicianDescription){
        this.politicianId = politicianId;
        this.politicianName = politicianName;
        this.politicianPosition = politicianPosition;
        this.politicianRegion = politicianRegion;
        this.politicianPhone = politicianPhone;
        this.politicianEmail = politicianEmail;

        this.politicianDescription = politicianDescription;
    }

    public String getPoliticianRegion() {
        return politicianRegion;
    }

    public void setPoliticianRegion(String politicianRegion) {
        this.politicianRegion = politicianRegion;
    }

    public int getPoliticianImage() {
        return politicianImage;
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
    public void setPoliticianImage(int politicianImage) {
        this.politicianImage = politicianImage;
    }
}
