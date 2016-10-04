package net.jebster.TornNotifications.model;

import android.support.annotation.Nullable;

import net.jebster.TornNotifications.tools.Observable;
import net.jebster.TornNotifications.tools.TimeUtils;

import java.io.Serializable;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornUser extends Observable implements Serializable {

    private int id;
    private String username;

    private String apiKey;

    private Bar energy;
    private Bar happy;
    private Bar nerve;
    private Bar life;
    private Travel travel;

    private Notifications notifications;
    private Cooldown cooldowns;

    // Error
    private String errorText;



    /*
    public TornUser(int id, String username, String apiKey, Bar energy, Bar happy, Bar nerve, Bar life, Travel travel, @Nullable Notifications notifications, long serverTime) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;

        this.energy = energy;
        this.happy = happy;
        this.nerve = nerve;
        this.life = life;

        this.travel = travel;
        this.notifications = notifications;

        TornUser.serverTime = serverTime;
        super.update();
    }

    public TornUser(String errorText) {
        this.errorText = errorText;
    }
    */

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

    public Bar getEnergy() {
        return energy;
    }

    public void setEnergy(Bar energy) {
        this.energy = energy;
    }

    public Bar getHappy() {
        return happy;
    }

    public void setHappy(Bar happy) {
        this.happy = happy;
    }

    public Bar getNerve() {
        return nerve;
    }

    public void setNerve(Bar nerve) {
        this.nerve = nerve;
    }

    public Bar getLife() {
        return life;
    }

    public void setLife(Bar life) {
        this.life = life;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public Cooldown getCooldowns() {
        return cooldowns;
    }

    public void setCooldowns(Cooldown cooldowns) {
        this.cooldowns = cooldowns;
    }

    public String getErrorText() {
        return errorText;
    }

    public TornUser setErrorText(String errorText) {
        this.errorText = errorText;
        return this;
    }

    public static class Bar{

        private int current;
        private int maximum;
        private int increment;
        private int interval;
        private int ticktime;
        private int fulltime;

        public Bar(int current, int maximum, int increment, int interval, int ticktime, int fulltime) {
            this.current = current;
            this.maximum = maximum;
            this.increment = increment;
            this.interval = interval;
            this.ticktime = ticktime;
            this.fulltime = fulltime;
        }

        public Bar(){

        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getMaximum() {
            return maximum;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }

        public int getIncrement() {
            return increment;
        }

        public void setIncrement(int increment) {
            this.increment = increment;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getTicktime() {
            return ticktime;
        }

        public void setTicktime(int ticktime) {
            this.ticktime = ticktime;
        }

        public int getFulltime() {
            return fulltime;
        }

        public void setFulltime(int fulltime) {
            this.fulltime = fulltime;
        }
    }

    public static class Travel{
        private String destination;
        private long timestamp;
        private long departed;
        private long time_left;

        public Travel(String destination, long timestamp, long departed, long time_left) {
            this.destination = destination;
            this.timestamp = timestamp;
            this.departed = departed;
            this.time_left = time_left;
        }

        public Travel(){}

        public String getTimeTravelLeft(){
            return TimeUtils.getStringTime(this.time_left);
        }

        public long getTravelTime(){
            return this.timestamp - this.departed;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public long getDeparted() {
            return departed;
        }

        public void setDeparted(long departed) {
            this.departed = departed;
        }

        public long getTime_left() {
            return time_left;
        }

        public void setTime_left(long time_left) {
            this.time_left = time_left;
        }
    }

    public static class Notifications{
        private int messages;
        private int events;
        private int awards;
        private int competition;

        public Notifications(int messages, int events, int awards, int competition) {
            this.messages = messages;
            this.events = events;
            this.awards = awards;
            this.competition = competition;
        }

        public Notifications(){}

        public int getMessages() {
            return messages;
        }

        public void setMessages(int messages) {
            this.messages = messages;
        }

        public int getEvents() {
            return events;
        }

        public void setEvents(int events) {
            this.events = events;
        }

        public int getAwards() {
            return awards;
        }

        public void setAwards(int awards) {
            this.awards = awards;
        }

        public int getCompetition() {
            return competition;
        }

        public void setCompetition(int competition) {
            this.competition = competition;
        }
    }

    public static class Cooldown{
        private int drug;
        private int medical;
        private int booster;

        public Cooldown(int drug, int medical, int booster) {
            this.drug = drug;
            this.medical = medical;
            this.booster = booster;
        }

        public Cooldown(){}

        public int getDrug() {
            return drug;
        }

        public void setDrug(int drug) {
            this.drug = drug;
        }

        public int getMedical() {
            return medical;
        }

        public void setMedical(int medical) {
            this.medical = medical;
        }

        public int getBooster() {
            return booster;
        }

        public void setBooster(int booster) {
            this.booster = booster;
        }
    }
}
