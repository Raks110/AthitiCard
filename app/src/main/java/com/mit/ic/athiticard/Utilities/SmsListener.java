package com.mit.ic.athiticard.Utilities;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsListener extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.KITKAT)

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("messageBody", intent.getAction());
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            try {
                String messageBody = "";
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    messageBody = smsMessage.getMessageBody();
                }
                Intent messageReceived = new Intent("SMS");
                messageReceived.putExtra("sms", messageBody);
                context.sendBroadcast(messageReceived); // when receiving it somewhere in your app, subString the additional text and leave only the code, then place it in the editText and do your verification
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
