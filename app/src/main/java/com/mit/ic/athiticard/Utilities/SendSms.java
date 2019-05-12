package com.mit.ic.athiticard.Utilities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SendSms extends AsyncTask<URL,String,String> {

    public static void sendMsg(String phoneNumber,int pin) {

        String mobile = "Mobile=9370232216";
        String password = "&Password=G2889B";
        String apiKey = "&Key=rakshdCuJ8tQRFgPY4HWO7sk";
        String message = "&Message=Your OTP for Athiti Card verification is " + pin;
        String toNum = "&To=" + phoneNumber;

        String data = mobile + password + apiKey + message + toNum;
        Log.e("DATA",data);

        try {
            URL url = new URL("https://smsapi.engineeringtgr.com/send/?" + data);
            new SendSms().execute(url);
        }
        catch(Exception e){}
    }

    @Override
    protected String doInBackground(URL...urls) {
        try {
            // Construct data

            // Send data
            HttpURLConnection conn = (HttpURLConnection)urls[0].openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            Log.e(TAG,e.toString());
            return e.toString();
        }
    }
}
