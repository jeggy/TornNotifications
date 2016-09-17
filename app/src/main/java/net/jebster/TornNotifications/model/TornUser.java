package net.jebster.TornNotifications.model;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornUser {

    public int id;
    public String username;

    public String apiKey;

    public int energy;
    public int maximumEnergy;
    public int happy;
    public int maximumHappy;
    public int nerve;
    public int maximumNerve;

    public String travelDestination;
    public int travelTimeLeft;

    // Error
    public String errorText;

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
}
