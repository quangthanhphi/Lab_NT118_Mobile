package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message),
                Toast.LENGTH_LONG).show();
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        String sms = "";
        SmsMessage smsMsg;
        for (int i = 0; i < messages.length; i++) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], "3gpp");
            } else {
                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
            }

            // Get message body and source address
            String msgBody = smsMsg.getMessageBody();
            String address = smsMsg.getDisplayOriginatingAddress();
            sms += address + "\n" + msgBody + "\n";
        }

        // Show messages in TextView
        tvContent.setText(sms);
    }

    private void initBroadcastReceiver() {
        // Create filter to listen to incoming messages
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // Create broadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {
            // Process when a message comes
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Make sure broadcastReceiver was created
        if (broadcastReceiver == null) initBroadcastReceiver();
        // Register Receiver
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unregister Receiver when app is destroyed
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReceiver();
    }
}
