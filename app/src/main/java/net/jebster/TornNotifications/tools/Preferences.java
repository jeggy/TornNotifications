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
        sd.setApiKey(prefs.getString("ApiKey", ""));
        sd.setUpdateSecs(Integer.parseInt(prefs.getString("UpdateInterval", "-1"))); // TODO: make proper check on this.
        sd.setStartOnBoot(prefs.getBoolean("StartOnBoot", false));

        sd.setEnergyNotification(prefs.getBoolean("EnergyNotification", false));
        sd.setHappyNotification(prefs.getBoolean("NerveNotification", false));
        sd.setNerveNotification(prefs.getBoolean("HappyNotification", false));
        sd.setTravelNotification(prefs.getBoolean("TravelNotification", false));
        sd.setEventsNotification(prefs.getBoolean("EventsNotification", false));
        sd.setCooldownNotification(prefs.getBoolean("CooldownNotification", false));

        sd.setSound(prefs.getBoolean("Sound", false));
        sd.setSoundUri(prefs.getString("NotificationSound", "default ringtone"));
        sd.setVibrate(prefs.getBoolean("Vibrate", false));
        sd.setLed(prefs.getBoolean("Led", false));

        return sd;
    }

}
