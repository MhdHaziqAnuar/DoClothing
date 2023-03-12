package com.example.donationusedclothing;

public class ModelDonation {

    private int upimages;
    private String clothestype;
    private String name;
    private String donationContact;
    private String itemSize;
    private String itemColor;
    private String meetupPoint;
    private String key;


    public ModelDonation(int upimages, String clothestype, String name, String donationContact, String itemSize,
                         String itemColor, String meetupPoint) {

        this.upimages = upimages;
        this.clothestype = clothestype;
        this.name = name;
        this.donationContact = donationContact;
        this.itemSize = itemSize;
        this.itemColor = itemColor;
        this.meetupPoint = meetupPoint;
    }

    public ModelDonation(){

    }

    public int getUpimages() {
        return upimages;
    }

    public void setUpimages(int upimages) {
        this.upimages = upimages;
    }

    public String getClothestype() {
        return clothestype;
    }

    public void setClothestype(String clothestype) {
        this.clothestype = clothestype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getDonationContact() {
        return donationContact;
    }

    public void setDonationContact(String donationContact) {
        this.donationContact = donationContact;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getMeetupPoint() {
        return meetupPoint;
    }

    public void setMeetupPoint(String meetupPoint) {
        this.meetupPoint = meetupPoint;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
