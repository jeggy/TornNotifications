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

public class EnergyTest {
    @Mock
    private SaveData sampleSaveData;
    @Mock
    private TornUser sampleLastUser;
    @Mock
    private TornUser sampleNowUser;
    @Mock
    private TornNotificationManager tnm;

    private TornUser.Bar farFromFullBar1;
    private TornUser.Bar farFromFullBar2;
    private TornUser.Bar almostToFullBar;
    private TornUser.Bar fullBar;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        farFromFullBar1 = new TornUser.Bar(105, 150, 5, 5, 5, 5);
        farFromFullBar2 = new TornUser.Bar(110, 150, 5, 5, 5, 5);
        almostToFullBar = new TornUser.Bar(145, 150, 5, 5, 5, 5);
        fullBar = new TornUser.Bar(150, 150, 5, 5, 5, 5);
    }

    @Test
    public void notificationsCheck_EnergyNotificationCalledTest(){
        // Arrange
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleLastUser.getEnergy()).thenReturn(almostToFullBar); //145);
        when(sampleNowUser.getEnergy()).thenReturn(fullBar); //150);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).EnergyNotification();
    }

    @Test
    public void notificationsCheck_AlreadyFullEnergy_NotCallEnergyNotification(){
        // Arrange
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleLastUser.getEnergy()).thenReturn(fullBar);
        when(sampleNowUser.getEnergy()).thenReturn(fullBar);
        when(sampleNowUser.getEnergy()).thenReturn(fullBar);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).EnergyNotification();
    }

    @Test
    public void notificationsCheck_NotEnoughEnergy_NotCallEnergyNotification(){
        // Arrange
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleLastUser.getEnergy()).thenReturn(farFromFullBar1);
        when(sampleNowUser.getEnergy()).thenReturn(farFromFullBar2);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).EnergyNotification();
    }

    @Test
    public void notificationsCheck_FullEnergyNotificationNotEnabled_NotCallEnergyNotification(){
        // Arrange
        when(sampleSaveData.EnergyNotification()).thenReturn(false);
        when(sampleLastUser.getEnergy()).thenReturn(almostToFullBar);
        when(sampleNowUser.getEnergy()).thenReturn(fullBar);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).EnergyNotification();
    }
}
