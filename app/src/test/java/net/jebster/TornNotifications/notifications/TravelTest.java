package net.jebster.TornNotifications.notifications;

import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.jebster.TornNotifications.notifications.NotificationSuite.nothing;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jeggy on 10/4/16.
 */

public class TravelTest {

    @Mock
    private SaveData sampleSaveData;
    @Mock
    private TornUser sampleLastUser;
    @Mock
    private TornUser sampleNowUser;
    @Mock
    private TornNotificationManager tnm;

    private TornUser.Travel flying1;
    private TornUser.Travel flying2;
    private TornUser.Travel notFlying;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        flying1 = new TornUser.Travel("Torn", 2, 25, 25 );
        flying2 = new TornUser.Travel("Torn", 2, 25, 10 );
        notFlying = new TornUser.Travel("Torn", 2, 25, 0 );
    }

    @Test
    public void notificationsCheck_Landed_TravelNotificationCalled(){
        // Arrange
        when(sampleSaveData.TravelNotification()).thenReturn(true);
        when(sampleLastUser.getTravel()).thenReturn(flying1);
        when(sampleNowUser.getTravel()).thenReturn(notFlying);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).TravelNotification();
    }

    @Test
    public void notificationsCheck_StillFlying_TravelNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.TravelNotification()).thenReturn(true);
        when(sampleLastUser.getTravel()).thenReturn(flying1);
        when(sampleNowUser.getTravel()).thenReturn(flying2);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).TravelNotification();
    }

    @Test
    public void notificationsCheck_NotFlying_TravelNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.TravelNotification()).thenReturn(true);
        when(sampleLastUser.getTravel()).thenReturn(notFlying);
        when(sampleNowUser.getTravel()).thenReturn(notFlying);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).TravelNotification();
    }

}
