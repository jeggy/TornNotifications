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
    }

    @Test
    public void notificationsCheck_EnergyNotificationCalledTest(){
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleTornUser.getEnergy()).thenReturn(145);
        when(sampleTornUserFull.getEnergy()).thenReturn(150);
        when(sampleTornUserFull.getMaximumEnergy()).thenReturn(150);

        doNothing().when(tnm).EnergyNotification();
        doNothing().when(tnm).HappyNotification();
        doNothing().when(tnm).NerveNotification();
        doNothing().when(tnm).TravelNotification();
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        tnm.notificationsCheck(sampleSaveData, sampleTornUserFull, sampleTornUser);

        verify(tnm).EnergyNotification();
    }

    @Test
    public void notificationsCheck_EnergyNotificationNotCalledTest(){
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleTornUser.getEnergy()).thenReturn(150);
        when(sampleTornUserFull.getEnergy()).thenReturn(150);
        when(sampleTornUserFull.getMaximumEnergy()).thenReturn(150);

        doNothing().when(tnm).EnergyNotification();
        doNothing().when(tnm).HappyNotification();
        doNothing().when(tnm).NerveNotification();
        doNothing().when(tnm).TravelNotification();
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        tnm.notificationsCheck(sampleSaveData, sampleTornUserFull, sampleTornUser);

        verify(tnm, never()).EnergyNotification();
    }
}
