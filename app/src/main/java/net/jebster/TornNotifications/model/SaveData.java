package net.jebster.TornNotifications.model;

import android.net.Uri;

/**
 * Created by jeggy on 9/10/16.
 */
public class SaveData {

    private String apiKey;
    private int updateSecs;
    private boolean startOnBoot;

    private boolean energyNotification;
    private boolean nerveNotification;
    private boolean happyNotification;
    private boolean travelNotification;
    private boolean eventsNotification;

    private boolean sound;
    private String _sound;
    private boolean vibrate;
    private boolean led;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getUpdateSecs() {
        return updateSecs;
    }

    public void setUpdateSecs(int updateSecs) {
        this.updateSecs = updateSecs;
    }

    public boolean StartOnBoot() {
        return startOnBoot;
    }

    public void setStartOnBoot(boolean startOnBoot) {
        this.startOnBoot = startOnBoot;
    }

    public boolean EnergyNotification() {
        return energyNotification;
    }

    public void setEnergyNotification(boolean energyNotification) {
        this.energyNotification = energyNotification;
    }

    public boolean NerveNotification() {
        return nerveNotification;
    }

    public void setNerveNotification(boolean nerveNotification) {
        this.nerveNotification = nerveNotification;
    }

    public boolean HappyNotification() {
        return happyNotification;
    }

    public void setHappyNotification(boolean happyNotification) {
        this.happyNotification = happyNotification;
    }

    public boolean TravelNotification() {
        return travelNotification;
    }

    public void setTravelNotification(boolean travelNotification) {
        this.travelNotification = travelNotification;
    }

    public boolean EventsNotification() {
        return eventsNotification;
    }

    public void setEventsNotification(boolean eventsNotification) {
        this.eventsNotification = eventsNotification;
    }

    public boolean Sound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean Led() {
        return led;
    }

    public void setLed(boolean led) {
        this.led = led;
    }

    public boolean Vibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }
}
