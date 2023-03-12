package com.example.donationusedclothing;

public class DataClass {
    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataSize;
    private String dataColor;
    private String dataMeetup;
    private String dataImage;
    private String key;

    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataSize, String dataColor, String dataMeetup, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataSize = dataSize;
        this.dataColor = dataColor;
        this.dataMeetup = dataMeetup;
        this.dataImage = dataImage;
    }

    public String getDataSize() {
        return dataSize;
    }

    public String getDataColor() {
        return dataColor;
    }

    public String getDataMeetup() {
        return dataMeetup;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataLang() {
        return dataLang;
    }
    public String getDataImage() {
        return dataImage;
    }


    public DataClass(){
    }
}
