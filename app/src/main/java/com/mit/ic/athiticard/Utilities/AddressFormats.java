package com.mit.ic.athiticard.Utilities;

import com.mit.ic.athiticard.Models.Address;
import com.mit.ic.athiticard.Models.CompanyAddress;

public class AddressFormats {

    public static String changeFormat(Address address){
        return address.getStreet() + ", Near " + address.getLandmark() + ", " + address.getCity() + ", " + address.getState() + " " + address.getPinCode();
    }

    public static String changeFormatPro(CompanyAddress address){
        return address.getStreet() + ", Near " + address.getLandmark() + ", " + address.getCity() + ", " + address.getState() + " " + address.getPinCode();
    }

}
