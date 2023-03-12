package com.example.donationusedclothing;

public class ModelRequest {
    private String itemRequested;
    private String requestedUser;
    private String requestedQuantity;
    private String requestPhone;
    private String key;


    public ModelRequest(){

    }

    public ModelRequest(String itemRequested, String requestedUser, String requestedQuantity, String requestPhone) {
        this.itemRequested = itemRequested;
        this.requestedUser = requestedUser;
        this.requestedQuantity = requestedQuantity;
        this.requestPhone = requestPhone;
    }


    public String getItemRequested() {
        return itemRequested;
    }

    public void setItemRequested(String itemRequested) {
        this.itemRequested = itemRequested;
    }

    public String getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(String requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getRequestPhone() {
        return requestPhone;
    }

    public void setRequestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
    }
}
