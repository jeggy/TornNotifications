package net.jebster.TornNotifications.tools;

import android.annotation.SuppressLint;

import net.jebster.TornNotifications.Exceptions.NegativeNumberException;

/**
 * Created by jeggy on 10/4/16.
 */

public class TimeUtils {

    @SuppressLint("DefaultLocale")
    public static String getStringTime(long s) {

        int hours = (int) s / 3600;
        int minutes = (int)  (s - (hours * 3600)) / 60;
        int seconds = (int) (s - (hours * 3600) - (minutes * 60));

        if(hours < 0 || minutes < 0 || seconds < 0)
            throw new NegativeNumberException("Only positive numbers supported");

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}


