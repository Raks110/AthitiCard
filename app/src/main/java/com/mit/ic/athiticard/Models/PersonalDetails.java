package com.mit.ic.athiticard.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNumber")
    @Expose
    private Integer phoneNumber;
    @SerializedName("address")
    @Expose
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
