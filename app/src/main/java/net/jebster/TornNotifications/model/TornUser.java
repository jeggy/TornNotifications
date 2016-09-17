package net.jebster.TornNotifications.model;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornUser {

    private int id;
    private String username;

    private String apiKey;

    private int energy;
    private int maximumEnergy;
    private int happy;
    private int maximumHappy;
    private int nerve;
    private int maximumNerve;

    private String travelDestination;
    private int travelTimeLeft;

    // Error
    private String errorText;

    public TornUser(int id, String username, String apiKey, int energy, int maximumEnergy, int happy, int maximumHappy, int nerve, int maximumNerve, String travelDestination, int travelTimeLeft) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;
        this.energy = energy;
        this.maximumEnergy = maximumEnergy;
        this.happy = happy;
        this.maximumHappy = maximumHappy;
        this.nerve = nerve;
        this.maximumNerve = maximumNerve;
        this.travelDestination = travelDestination;
        this.travelTimeLeft = travelTimeLeft;
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

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
