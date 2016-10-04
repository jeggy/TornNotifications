package net.jebster.TornNotifications.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeggy on 9/30/16.
 */

public abstract class Observable {

    private static HashMap<String, Observer> observers = new HashMap<>();

    public Observable(){
        update();
    }

    public void update(){
        for (Map.Entry<String, Observer> entry : observers.entrySet()) {
            entry.getValue().update();
        }
    }

    public void addObserver(String s, Observer o){
        observers.put(s, o);
    }
}


