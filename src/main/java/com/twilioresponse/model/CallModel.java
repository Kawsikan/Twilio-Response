package com.twilioresponse.model;

public class CallModel {

    private String mobileNumber;

    public CallModel() {
        super();
    }

    public CallModel(String mobileNumber) {
        super();
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
