package net.jebster.TornNotifications;

import android.support.v4.app.NotificationCompat;

import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Created by jeggy on 9/17/16.
 */
public class TornNotificationManagerTest {

    @Mock
    SaveData sampleSaveData;
    @Mock
    TornUser sampleTornUser;
    @Mock
    TornUser sampleTornUserFull;
    @Mock
    TornNotificationManager tnm;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        /*
        sampleSaveData = new SaveData(
                "ApiKey",   // Key
                10,         // Update interval
                true,       // OnBoot
                true,       // Energy Notification
                true,       // Nerve Notification
                true,       // Happy Notification
                true,       // Travel Notification
                true,       // Events Notification
                true,       // Sound
                "Sound",    // Sound location
                true,       // Vibrate
                true        // Led
        );

        sampleTornUser = new TornUser(
                4,          // id
                "username", // Username
                "ApiKey",   // Apikey
                145,        // Energy
                150,        // Maximum energy
                145,        // Happy
                150,        // Maximum happy
                145,        // Nerve
                150,        // Maximum nerve
                "Torn",     // Travel destination
                200         // Travel time left
        );

        sampleTornUserFull = new TornUser(
                4,          // id
                "username", // Username
                "ApiKey",   // Apikey
                150,        // Energy
                150,        // Maximum energy
                150,        // Happy
                150,        // Maximum happy
                150,        // Nerve
                150,        // Maximum nerve
                "Torn",     // Travel destination
                0         // Travel time left
        );
        */
    }

    @Test
    public void notificationsCheck_TravelNotificationCalledTest(){
        when(sampleSaveData.getSound()).thenReturn(null);
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleTornUser.getEnergy()).thenReturn(145);
        when(sampleTornUserFull.getEnergy()).thenReturn(150);

        doNothing().when(tnm).EnergyNotification();
        doNothing().when(tnm).HappyNotification();
        doNothing().when(tnm).NerveNotification();
        doNothing().when(tnm).TravelNotification();

        tnm.notificationsCheck(sampleSaveData, sampleTornUserFull, sampleTornUser);

        verify(tnm).EnergyNotification();

    }
}
