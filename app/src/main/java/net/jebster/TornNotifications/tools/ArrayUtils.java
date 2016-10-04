package net.jebster.TornNotifications.tools;

/**
 * Created by jeggy on 10/4/16.
 */

public class ArrayUtils {

    public static boolean contains(String[] array, String contains){
        for (String anArray : array) {
            if (anArray.equals(contains))
                return true;
        }
        return false;
    }

}
