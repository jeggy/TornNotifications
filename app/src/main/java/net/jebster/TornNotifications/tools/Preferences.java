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
        SaveData sd = new SaveData();
        sd.ApiKey = prefs.getString("ApiKey", "");
        sd.UpdateSecs = Integer.parseInt(prefs.getString("UpdateInterval", "-1")); // TODO: make proper check on this.
        sd.StartOnBoot = prefs.getBoolean("StartOnBoot", false);

        sd.EnergyNotification = prefs.getBoolean("EnergyNotification", false);
        sd.HappyNotification = prefs.getBoolean("NerveNotification", false);
        sd.NerveNotification = prefs.getBoolean("HappyNotification", false);
        sd.TravelNotification = prefs.getBoolean("TravelNotification", false);
        sd.EventsNotification = prefs.getBoolean("EventsNotification", false);
        sd.CoolDownNotification = prefs.getBoolean("CooldownNotification", false);

        sd.Sound = prefs.getBoolean("Sound", false);
        sd._sound = prefs.getString("NotificationSound", "default ringtone");
        sd.Vibrate = prefs.getBoolean("Vibrate", false);
        sd.Led = prefs.getBoolean("Led", false);

        return sd;
    }

}
