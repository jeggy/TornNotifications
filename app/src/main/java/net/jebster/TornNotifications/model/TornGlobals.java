package net.jebster.TornNotifications.model;

import java.util.HashMap;

/**
 * Created by jeggy on 9/24/16.
 */
public class TornGlobals {

    /* Not in use
    // http://www.torn.com/wiki/Travel
    public HashMap<String, FlightInfo[]> flightTimes = new HashMap<String, FlightInfo[]>(){{
        put("Mexico", new FlightInfo[]{
                new FlightInfo(0, 26, 0, 6500),
                new FlightInfo(0, 18, 0, 0),
                new FlightInfo(0, 13, 0, 0),
                new FlightInfo(0, 8, 0, -1),
        });
        put("Cayman Islands", new FlightInfo[]{
                new FlightInfo(0, 35, 0, 10000),
                new FlightInfo(0, 25, 0, 0),
                new FlightInfo(0, 18, 0, 0),
                new FlightInfo(0, 11, 0, -1),
        });
        put("Canada", new FlightInfo[]{
                new FlightInfo(0, 41, 0, 9000),
                new FlightInfo(0, 29, 0, 0),
                new FlightInfo(0, 20, 0, 0),
                new FlightInfo(0, 12, 0, -1),
        });
        put("Hawaii", new FlightInfo[]{
                new FlightInfo(2, 14, 0, 11000),
                new FlightInfo(1, 34, 0, 0),
                new FlightInfo(1, 7, 0, 0),
                new FlightInfo(0, 40, 0, -1),
        });
        put("United Kingdom", new FlightInfo[]{
                new FlightInfo(2, 39, 0, 18000),
                new FlightInfo(1, 51, 0, 0),
                new FlightInfo(1, 20, 0, 0),
                new FlightInfo(0, 48, 0, -1),
        });
        put("Argentina", new FlightInfo[]{
                new FlightInfo(2, 47, 0, 21000),
                new FlightInfo(1, 57, 0, 0),
                new FlightInfo(1, 23, 0, 0),
                new FlightInfo(0, 50, 0, -1),
        });
        put("Switzerland", new FlightInfo[]{
                new FlightInfo(2, 55, 0, 27000),
                new FlightInfo(2, 3, 0, 0),
                new FlightInfo(1, 28, 0, 0),
                new FlightInfo(0, 53, 0, -1),
        });
        put("Japan", new FlightInfo[]{
                new FlightInfo(3, 45, 0, 32000),
                new FlightInfo(2, 38, 0, 0),
                new FlightInfo(1, 53, 0, 0),
                new FlightInfo(1, 8, 0, -1),
        });
        put("China", new FlightInfo[]{
                new FlightInfo(4, 2, 0, 35000),
                new FlightInfo(2, 49, 0, 0),
                new FlightInfo(2, 1, 0, 0),
                new FlightInfo(1, 12, 0, -1),
        });
        put("United Arab Emirates", new FlightInfo[]{
                new FlightInfo(4, 31, 0, 32000),
                new FlightInfo(3, 10, 0, 0),
                new FlightInfo(2, 15, 0, 0),
                new FlightInfo(1, 21, 0, -1),
        });
        put("South Africa", new FlightInfo[]{
                new FlightInfo(4, 57, 0, 40000),
                new FlightInfo(3, 28, 0, 0),
                new FlightInfo(2, 29, 0, 0),
                new FlightInfo(1, 29, 0, -1)
        });
    }};
    */

    private static TornGlobals self;

    // Singleton
    public static TornGlobals instance(){
        if(self == null) self = new TornGlobals();
        return self;
    }

    private TornGlobals(){}

}
