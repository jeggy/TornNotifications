package net.jebster.TornNotifications.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.jebster.TornNotifications.model.SaveData;

/**
 * Created by jeggy on 9/10/16.
 */
public class Preferences {

    public static SaveData LoadData(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return new SaveData(prefs.getString("ApiKey", ""),
                Integer.parseInt(prefs.getString("UpdateInterval", "-1")),
                prefs.getBoolean("StartOnBoot", false),

                prefs.getBoolean("EnergyNotification", false),
                prefs.getBoolean("NerveNotification", false),
                prefs.getBoolean("HappyNotification", false),
                prefs.getBoolean("TravelNotification", false),
                prefs.getBoolean("EventsNotification", false),

                prefs.getBoolean("Sound", false),
                prefs.getString("NotificationSound", "default ringtone"),
                prefs.getBoolean("Vibrate", false),
                prefs.getBoolean("Led", false)
        );
    }

}
