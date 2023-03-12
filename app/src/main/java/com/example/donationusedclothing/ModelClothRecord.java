package com.example.donationusedclothing;

public class ModelClothRecord {
    private String dataDonorname;
    private String dataDonorPhonenum;
    private String dataDonatedDesc;
    private String dataDonatedQuantity;
    private String dataDonatedImage;
    private String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDataDonorname() {
        return dataDonorname;
    }

    public void setDataDonorname(String dataDonorname) {
        this.dataDonorname = dataDonorname;
    }

    public String getDataDonorPhonenum() {
        return dataDonorPhonenum;
    }

    public void setDataDonorPhonenum(String dataDonorPhonenum) {
        this.dataDonorPhonenum = dataDonorPhonenum;
    }

    public String getDataDonatedDesc() {
        return dataDonatedDesc;
    }

    public void setDataDonatedDesc(String dataDonatedDesc) {
        this.dataDonatedDesc = dataDonatedDesc;
    }

    public String getDataDonatedQuantity() {
        return dataDonatedQuantity;
    }

    public void setDataDonatedQuantity(String dataDonatedQuantity) {
        this.dataDonatedQuantity = dataDonatedQuantity;
    }

    public String getDataDonatedImage() {
        return dataDonatedImage;
    }

    public void setDataDonatedImage(String dataDonatedImage) {
        this.dataDonatedImage = dataDonatedImage;
    }

    public ModelClothRecord(String dataDonorname, String dataDonorPhonenum, String dataDonatedDesc, String dataDonatedQuantity, String dataDonatedImage) {
        this.dataDonorname = dataDonorname;
        this.dataDonorPhonenum = dataDonorPhonenum;
        this.dataDonatedDesc = dataDonatedDesc;
        this.dataDonatedQuantity = dataDonatedQuantity;
        this.dataDonatedImage = dataDonatedImage;
    }

    public ModelClothRecord(){
    }
}
