package net.jebster.TornNotifications.model;

import android.net.Uri;

/**
 * Created by jeggy on 9/10/16.
 */
public class SaveData {

    public String apiKey;
    public int updateSecs;
    public boolean startOnBoot;

    public boolean energyNotification;
    public boolean nerveNotification;
    public boolean happyNotification;
    public boolean travelNotification;
    public boolean eventsNotification;

    public boolean sound;
    String _sound;
    public boolean vibrate;
    public boolean led;

    public SaveData(String apiKey, int updateSecs, boolean startOnBoot, boolean energyNotification, boolean nerveNotification, boolean happyNotification, boolean travelNotification, boolean eventsNotification, boolean sound, String _sound, boolean vibrate, boolean led) {
        this.apiKey = apiKey;
        this.updateSecs = updateSecs;
        this.startOnBoot = startOnBoot;
        this.energyNotification = energyNotification;
        this.nerveNotification = nerveNotification;
        this.happyNotification = happyNotification;
        this.travelNotification = travelNotification;
        this.eventsNotification = eventsNotification;
        this.sound = sound;
        this._sound = _sound;
        this.vibrate = vibrate;
        this.led = led;
    }

    public Uri getSound(){
        return Uri.parse(_sound);
    }
}
