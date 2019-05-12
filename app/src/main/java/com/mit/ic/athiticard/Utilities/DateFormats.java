package com.mit.ic.athiticard.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {

    public static String changeFormat(Date date){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
