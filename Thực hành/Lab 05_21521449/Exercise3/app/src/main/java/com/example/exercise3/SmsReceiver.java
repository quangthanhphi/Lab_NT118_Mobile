package com.example.exercise3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    @Override
    public void onReceive(Context context, Intent intent) {
        String queryString = "Are you OK?".toLowerCase();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                ArrayList<String> addresses = new ArrayList<>();
                for (Object pdu : pdus) {
                    SmsMessage message = getIncomingMessage(pdu, bundle);
                    if (message != null) {
                        String messageBody = message.getMessageBody();
                        if (messageBody != null && messageBody.toLowerCase().contains(queryString)) {
                            addresses.add(message.getOriginatingAddress());
                        }
                    }
                }
                if (!addresses.isEmpty()) {
                    Intent mainActivityIntent = new Intent(context, MainActivity.class);
                    mainActivityIntent.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mainActivityIntent);
                }
            }
        }
    }

    private SmsMessage getIncomingMessage(Object pdu, Bundle bundle) {
        SmsMessage message;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            message = SmsMessage.createFromPdu((byte[]) pdu, format);
        } else {
            message = SmsMessage.createFromPdu((byte[]) pdu);
        }
        return message;
    }

}
