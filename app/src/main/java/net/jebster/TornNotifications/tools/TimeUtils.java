package net.jebster.TornNotifications.tools;

import android.annotation.SuppressLint;

/**
 * Created by jeggy on 10/4/16.
 */

public class TimeUtils {

    @SuppressLint("DefaultLocale")
    public static String getStringTime(long s){

        int hours = (int) s / 3600;
        int minutes = (int)  (s - (hours * 3600)) / 60;
        int seconds = (int) (s - (hours * 3600) - (minutes * 60));

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
