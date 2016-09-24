package net.jebster.TornNotifications.model;

import java.io.Serializable;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornUser implements Serializable {

    private int id;
    private String username;

    private String apiKey;

    private Bar energy;
    private Bar happy;
    private Bar nerve;
    private Bar life;
    private Travel travel;

    // Error
    private String errorText;

    public TornUser(int id, String username, String apiKey, Bar energy, Bar happy, Bar nerve, Bar life, Travel travel) {
        this.id = id;
        this.username = username;
        this.apiKey = apiKey;

        this.energy = energy;
        this.happy = happy;
        this.nerve = nerve;
        this.life = life;

        this.travel = travel;
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

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
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
}
