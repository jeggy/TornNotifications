package net.jebster.TornNotifications.model;

/**
 * Created by jeggy on 9/24/16.
 */

public class FlightInfo {

    private int hours;
    private int minutes;
    private int seconds;
    private int price;

    public FlightInfo(int h, int m, int s, int p){
        hours = h;
        minutes = m;
        seconds = s;
        price = p;
    }

    public int getTotalSeconds(){
        return (((hours*60)+minutes)*60)+seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getPrice() {
        return price;
    }

}
