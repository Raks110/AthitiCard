package com.mit.ic.athiticard.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetails {

    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyNumber")
    @Expose
    private Integer companyNumber;
    @SerializedName("employerName")
    @Expose
    private String employerName;
    @SerializedName("companyAddress")
    @Expose
    private CompanyAddress companyAddress;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public CompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(CompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }

}
