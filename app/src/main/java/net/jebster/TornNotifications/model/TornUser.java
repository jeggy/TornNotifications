package net.jebster.TornNotifications.model;

import java.io.Serializable;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornUser implements Serializable {

    private int id;
    private String username;

    private String apiKey;

    private int energy;
    private int maximumEnergy;
    private int happy;
    private int maximumHappy;
    private int nerve;
    private int maximumNerve;
    private int life;
    private int maximumLife;

    private String travelDestination;
    private int travelTimeLeft;
    private int travelTime;

    // Error
    private String errorText;

    public TornUser(int id, String username, String apiKey, int energy, int maximumEnergy, int happy, int maximumHappy, int nerve, int maximumNerve, int life, int maximumLife, String travelDestination, int travelTimeLeft, int travelTime) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;
        this.energy = energy;
        this.maximumEnergy = maximumEnergy;
        this.happy = happy;
        this.maximumHappy = maximumHappy;
        this.nerve = nerve;
        this.maximumNerve = maximumNerve;
        this.life = life;
        this.maximumLife = maximumLife;
        this.travelDestination = travelDestination;
        this.travelTimeLeft = travelTimeLeft;
        this.travelTime = travelTime;
    }

    public TornUser(String errorText) {
        this.errorText = errorText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaximumEnergy() {
        return maximumEnergy;
    }

    public void setMaximumEnergy(int maximumEnergy) {
        this.maximumEnergy = maximumEnergy;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getMaximumHappy() {
        return maximumHappy;
    }

    public void setMaximumHappy(int maximumHappy) {
        this.maximumHappy = maximumHappy;
    }

    public int getNerve() {
        return nerve;
    }

    public void setNerve(int nerve) {
        this.nerve = nerve;
    }

    public int getMaximumNerve() {
        return maximumNerve;
    }

    public void setMaximumNerve(int maximumNerve) {
        this.maximumNerve = maximumNerve;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaximumLife() {
        return maximumLife;
    }

    public void setMaximumLife(int maximumLife) {
        this.maximumLife = maximumLife;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public int getTravelTimeLeft() {
        return travelTimeLeft;
    }

    public void setTravelTimeLeft(int travelTimeLeft) {
        this.travelTimeLeft = travelTimeLeft;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
