package com.example.donationusedclothing;

public class ModelAdminDonor {

    private String donorname;
    private String donorphonenumber;
    private String donorcategory;
    private String dkey;

    public ModelAdminDonor(){
    }

    public ModelAdminDonor(String donorname, String donorphonenumber, String donorcategory) {
        this.donorname = donorname;
        this.donorphonenumber = donorphonenumber;
        this.donorcategory = donorcategory;
    }

    public String getDonorname() {
        return donorname;
    }

    public void setDonorname(String donorname) {
        this.donorname = donorname;
    }

    public String getDonorphonenumber() {
        return donorphonenumber;
    }

    public void setDonorphonenumber(String donorphonenumber) {
        this.donorphonenumber = donorphonenumber;
    }

    public String getDonorcategory() {
        return donorcategory;
    }

    public void setDonorcategory(String donorcategory) {
        this.donorcategory = donorcategory;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
    }
}
