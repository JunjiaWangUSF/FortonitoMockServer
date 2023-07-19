package com.fortunito.resourceServer.response;

public class UserDetails {
    private String userFirstName;
    private String userId;
    private  String userLastName;

    public UserDetails(String userFirstName,  String userLastName, String userId) {
        this.userFirstName = userFirstName;
        this.userId = userId;
        this.userLastName = userLastName;
    }
    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
