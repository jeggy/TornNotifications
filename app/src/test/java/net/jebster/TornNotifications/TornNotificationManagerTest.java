package net.jebster.TornNotifications;

import net.jebster.TornNotifications.model.SaveData;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.TornNotificationManager;

import org.junit.Before;
import org.junit.Test;
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
    TornUser sampleLastUser;
    @Mock
    TornUser sampleNowUser;
    @Mock
    TornNotificationManager tnm;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    /***************************************************/
    /*                                                 */
    /*                    Helpers                      */
    /*                                                 */
    /***************************************************/

    private void nothing(TornNotificationManager tnm){
        doNothing().when(tnm).EnergyNotification();
        doNothing().when(tnm).HappyNotification();
        doNothing().when(tnm).NerveNotification();
        doNothing().when(tnm).TravelNotification();
    }

    /***************************************************/
    /*                                                 */
    /*           Energy Notification Tests             */
    /*                                                 */
    /***************************************************/

    @Test
    public void notificationsCheck_EnergyNotificationCalledTest(){
        // Arrange
        when(sampleSaveData.EnergyNotification()).thenReturn(true);
        when(sampleLastUser.getEnergy()).thenReturn(145);
        when(sampleNowUser.getEnergy()).thenReturn(150);
        when(sampleNowUser.getMaximumEnergy()).thenReturn(150);

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
        when(sampleLastUser.getEnergy()).thenReturn(150);
        when(sampleNowUser.getEnergy()).thenReturn(150);
        when(sampleNowUser.getMaximumEnergy()).thenReturn(150);

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
        when(sampleLastUser.getEnergy()).thenReturn(120);
        when(sampleNowUser.getEnergy()).thenReturn(125);
        when(sampleNowUser.getMaximumEnergy()).thenReturn(150);

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
        when(sampleLastUser.getEnergy()).thenReturn(145);
        when(sampleNowUser.getEnergy()).thenReturn(150);
        when(sampleNowUser.getMaximumEnergy()).thenReturn(150);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).EnergyNotification();
    }



    /***************************************************/
    /*                                                 */
    /*           Travel Notification Tests             */
    /*                                                 */
    /***************************************************/

    @Test
    public void notificationsCheck_Landed_TravelNotificationCalled(){
        // Arrange
        when(sampleSaveData.TravelNotification()).thenReturn(true);
        when(sampleLastUser.getTravelTimeLeft()).thenReturn(20);
        when(sampleNowUser.getTravelTimeLeft()).thenReturn(0);

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
        when(sampleLastUser.getTravelTimeLeft()).thenReturn(500);
        when(sampleNowUser.getTravelTimeLeft()).thenReturn(350);

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
        when(sampleLastUser.getTravelTimeLeft()).thenReturn(0);
        when(sampleNowUser.getTravelTimeLeft()).thenReturn(0);

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, never()).TravelNotification();
    }

}
