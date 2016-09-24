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

    private TornUser.Bar farFromFullBar1;
    private TornUser.Bar farFromFullBar2;
    private TornUser.Bar almostToFullBar;
    private TornUser.Bar fullBar;

    private TornUser.Travel flying1;
    private TornUser.Travel flying2;
    private TornUser.Travel notFlying;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        farFromFullBar1 = new TornUser.Bar(105, 150, 5, 5, 5, 5);
        farFromFullBar2 = new TornUser.Bar(110, 150, 5, 5, 5, 5);
        almostToFullBar = new TornUser.Bar(145, 150, 5, 5, 5, 5);
        fullBar = new TornUser.Bar(150, 150, 5, 5, 5, 5);
        flying1 = new TornUser.Travel("Torn", 2, 25, 25 );
        flying2 = new TornUser.Travel("Torn", 2, 25, 10 );
        notFlying = new TornUser.Travel("Torn", 2, 25, 0 );

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



    /***************************************************/
    /*                                                 */
    /*           Travel Notification Tests             */
    /*                                                 */
    /***************************************************/

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
