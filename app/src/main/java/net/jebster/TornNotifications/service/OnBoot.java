package net.jebster.TornNotifications.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import net.jebster.TornNotifications.tools.Preferences;

/**
 * Created by jeggy on 9/17/16.
 */
public class OnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OnBoot", "Booted: Starting Torn Notifications Service");
        if(Preferences.LoadData(context).StartOnBoot())
            context.startService(new Intent(context, TornBackgroundService.class));
    }
}
