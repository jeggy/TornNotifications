package net.jebster.TornNotifications.model;

/**
 * Created by jeggy on 9/24/16.
 */

public class Globals {

    public static final String PACKAGE_NAME = "net.jebster.TornNotifications";

    public static final String EXTRA_TORN_USER = PACKAGE_NAME + ".TORN_USER";

    public static final String INTENT_FILTER_TORN_USER = PACKAGE_NAME + ".broadcast.TORN_USER";

    private static TornUser user;

    public static TornUser User(){
        if(user == null)
            user = new TornUser().setErrorText("Hasn't loaded anything yet");
        return user;
    }

    public static TornUser User(TornUser user){
        Globals.user = user;
        return user;
    }

}
