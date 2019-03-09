package com.mit.ic.athiticard.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("cardNumber")
    @Expose
    private Integer cardNumber;
    @SerializedName("aadharNumber")
    @Expose
    private Integer aadharNumber;
    @SerializedName("panNumber")
    @Expose
    private String panNumber;
    @SerializedName("personalDetails")
    @Expose
    private PersonalDetails personalDetails;
    @SerializedName("jobDetails")
    @Expose
    private JobDetails jobDetails;

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(Integer aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public JobDetails getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(JobDetails jobDetails) {
        this.jobDetails = jobDetails;
    }

}
