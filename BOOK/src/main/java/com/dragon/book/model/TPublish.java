package com.dragon.book.model;

public class TPublish {
    private Integer pubId;

    private String pubName;

    private String pubNumber;

    private String pubEmail;

    private String pubPhone;

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName == null ? null : pubName.trim();
    }

    public String getPubNumber() {
        return pubNumber;
    }

    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber == null ? null : pubNumber.trim();
    }

    public String getPubEmail() {
        return pubEmail;
    }

    public void setPubEmail(String pubEmail) {
        this.pubEmail = pubEmail == null ? null : pubEmail.trim();
    }

    public String getPubPhone() {
        return pubPhone;
    }

    public void setPubPhone(String pubPhone) {
        this.pubPhone = pubPhone == null ? null : pubPhone.trim();
    }
}