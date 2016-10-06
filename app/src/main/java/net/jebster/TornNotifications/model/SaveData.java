package net.jebster.TornNotifications.model;

import android.net.Uri;

/**
 * Created by jeggy on 9/10/16.
 */
public class SaveData {

    private String ApiKey;
    private int UpdateSecs = 10;
    private boolean StartOnBoot;

    private boolean EnergyNotification;
    private boolean HappyNotification;
    private boolean NerveNotification;
    private boolean TravelNotification;
    private boolean EventsNotification;
    private boolean CoolDownNotification;

    private boolean Sound;
    private String _sound;
    private boolean Vibrate;
    private boolean Led;

    // TODO: Look at these Getters/Setters and fix names n' stuff around the project.
    public void setSoundUri(String sound){this._sound = sound;}

    public Uri getSound(){
        return Uri.parse(_sound);
    }

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String apiKey) {
        this.ApiKey = apiKey;
    }

    public int getUpdateSecs() {
        return UpdateSecs;
    }

    public void setUpdateSecs(int updateSecs) {
        this.UpdateSecs = updateSecs;
    }

    public boolean StartOnBoot() {
        return StartOnBoot;
    }

    public void setStartOnBoot(boolean startOnBoot) {
        this.StartOnBoot = startOnBoot;
    }

    public boolean EnergyNotification() {
        return EnergyNotification;
    }

    public void setEnergyNotification(boolean energyNotification) {
        this.EnergyNotification = energyNotification;
    }

    public boolean NerveNotification() {
        return NerveNotification;
    }

    public void setNerveNotification(boolean nerveNotification) {
        this.NerveNotification = nerveNotification;
    }

    public boolean HappyNotification() {
        return HappyNotification;
    }

    public void setHappyNotification(boolean happyNotification) {
        this.HappyNotification = happyNotification;
    }

    public boolean TravelNotification() {
        return TravelNotification;
    }

    public void setTravelNotification(boolean travelNotification) {
        this.TravelNotification = travelNotification;
    }

    public boolean EventsNotification() {
        return EventsNotification;
    }

    public void setEventsNotification(boolean eventsNotification) {
        this.EventsNotification = eventsNotification;
    }

    public boolean Sound() {
        return Sound;
    }

    public void setSound(boolean sound) {
        this.Sound = sound;
    }

    public boolean Led() {
        return Led;
    }

    public void setLed(boolean led) {
        this.Led = led;
    }

    public boolean Vibrate() {
        return Vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.Vibrate = vibrate;
    }

    public boolean CooldownNotification(){return CoolDownNotification;}

    public void setCooldownNotification(boolean value){this.CoolDownNotification = value;}


}
