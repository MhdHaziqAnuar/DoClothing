package com.example.donationusedclothing;

public class ModelAdminRecipient {
    private String recipientname;
    private String recipientphonenumber;
    private String recipientcategory;
    private String rkey;

    public ModelAdminRecipient(String recipientname, String recipientphonenumber, String recipientcategory) {
        this.recipientname = recipientname;
        this.recipientphonenumber = recipientphonenumber;
        this.recipientcategory = recipientcategory;
    }

    public ModelAdminRecipient(){
    }

    public String getRecipientname() {
        return recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname;
    }

    public String getRecipientphonenumber() {
        return recipientphonenumber;
    }

    public void setRecipientphonenumber(String recipientphonenumber) {
        this.recipientphonenumber = recipientphonenumber;
    }

    public String getRecipientcategory() {
        return recipientcategory;
    }

    public void setRecipientcategory(String recipientcategory) {
        this.recipientcategory = recipientcategory;
    }

    public String getRkey() {
        return rkey;
    }

    public void setRkey(String rkey) {
        this.rkey = rkey;
    }
}
