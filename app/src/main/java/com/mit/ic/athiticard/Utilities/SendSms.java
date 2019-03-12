package com.mit.ic.athiticard.Utilities;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SendSms {
    public static String sendMsg(String phoneNumber,int pin) {
        try {
            // Construct data
            String apiKey = "apikey=" + "/xoct6wXvBw-zBENiKx0opMW70VJIJwvhIey5kS9Hh";
            String message = "&message=" + "Athiti OTP is: " + Integer.toString(pin);
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=91" + phoneNumber;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            Log.e(TAG,data);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}