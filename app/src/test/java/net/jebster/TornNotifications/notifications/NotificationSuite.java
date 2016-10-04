package net.jebster.TornNotifications.notifications;

import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import static org.mockito.Mockito.*;

/**
 * Created by jeggy on 9/17/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EnergyTest.class,
        TravelTest.class,
        EventsTest.class,
        CooldownsTest.class
})
public class NotificationSuite {


    /****************************************/
    /*                                      */
    /*            Static Helpers            */
    /*                                      */
    /****************************************/

    public static void nothing(TornNotificationManager tnm){
        doNothing().when(tnm).EnergyNotification();
        doNothing().when(tnm).HappyNotification();
        doNothing().when(tnm).NerveNotification();
        doNothing().when(tnm).TravelNotification();
        doNothing().when(tnm).EventNotification();

        doNothing().when(tnm).DrugNotification();
        doNothing().when(tnm).MedicalNotification();
        doNothing().when(tnm).BoosterNotification();
    }

}


