package za.co.abiri.abiridata.Models;

import com.google.firebase.database.ServerValue;

public class Postb {

    //Class for post functionality
    private String PostKey;
    private String title;
    private String description;
    private String coordinates;
    private String picture;
    private String userId;
    //private String userPhoto;
    private Object timeStamp;
    private String userName;

    // Additional fields
    private String latitude;
    private String longitude;


    //Constructors
    public Postb(String title, String description, String coordinates, String picture, String userId, String userName,
                 String latitude, String longitude) {

        this.title = title;
        this.description = description;
        this.coordinates = coordinates;
        this.picture = picture;
        this.userId = userId;
        //this.userPhoto = userPhoto;
        this.timeStamp = ServerValue.TIMESTAMP;
        this.userName = userName;

        // Additional fields
        this.latitude = latitude;
        this.longitude = longitude;


    }

    public Postb() {

    }

    //PostKey Getter and Setter
    public String getPostKey() {
        return PostKey;
    }

    public void setPostKey(String postKey) {
        PostKey = postKey;
    }
    //PostKey

    //Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }

    /*
    public String getUserPhoto() {
        return userPhoto;
    }

     */

    public Object getTimeStamp() {
        return timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    //SETTERS
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /*
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    */

    // Additional field getters and setters
    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
