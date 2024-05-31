package com.example.exercise2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class PowerStateChangeReceiver extends BroadcastReceiver {
    public static final String POWER_STATE_CHANGED = "com.example.exercise2.POWER_STATE_CHANGED";
    public static final String EXTRA_POWER_STATE = "extra_power_state";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent localIntent = new Intent(POWER_STATE_CHANGED);

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show();
            localIntent.putExtra(EXTRA_POWER_STATE, "Power Connected");
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show();
            localIntent.putExtra(EXTRA_POWER_STATE, "Power Disconnected");
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent);
    }
}
