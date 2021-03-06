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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jeggy on 10/4/16.
 */

public class EventsTest {
    @Mock
    private SaveData sampleSaveData;
    @Mock
    private TornUser sampleLastUser;
    @Mock
    private TornUser sampleNowUser;
    @Mock
    private TornNotificationManager tnm;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void notificationCheck_NewEventReceived_EventNotificationCalled(){
        // Arrange
        when(sampleSaveData.EventsNotification()).thenReturn(true);
        when(sampleLastUser.getNotifications()).thenReturn(new TornUser.Notifications(0,0,0,0));
        when(sampleNowUser.getNotifications()).thenReturn(new TornUser.Notifications(0,1,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).EventNotification();
    }

    @Test
    public void notificationCheck_NewEventReceivedWithDisabled_EventNotificationCalled(){
        // Arrange
        when(sampleSaveData.EventsNotification()).thenReturn(false);
        when(sampleLastUser.getNotifications()).thenReturn(new TornUser.Notifications(0,0,0,0));
        when(sampleNowUser.getNotifications()).thenReturn(new TornUser.Notifications(0,1,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).EventNotification();
    }

    @Test
    public void notificationCheck_AlreadyEventReceived_EventNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.EventsNotification()).thenReturn(true);
        when(sampleLastUser.getNotifications()).thenReturn(new TornUser.Notifications(0,1,0,0));
        when(sampleNowUser.getNotifications()).thenReturn(new TornUser.Notifications(0,1,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).EventNotification();
    }

    @Test
    public void notificationCheck_TwoMoreEventReceived_EventNotificationCalled(){
        // Arrange
        when(sampleSaveData.EventsNotification()).thenReturn(true);
        when(sampleLastUser.getNotifications()).thenReturn(new TornUser.Notifications(0,1,0,0));
        when(sampleNowUser.getNotifications()).thenReturn(new TornUser.Notifications(0,3,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).EventNotification();
    }
}
