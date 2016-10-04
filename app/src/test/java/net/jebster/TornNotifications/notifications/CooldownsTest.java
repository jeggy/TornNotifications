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

public class CooldownsTest {
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

    /****************************************/
    /*                                      */
    /*              Drug Tests              */
    /*                                      */
    /****************************************/

    @Test
    public void notificationCheck_CooldownDrugFinished_CooldownsNotificationCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(11,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).DrugNotification();
    }

    @Test
    public void notificationCheck_CooldownDrugFinishedSettingDisabled_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(false);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(11,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).DrugNotification();
    }

    @Test
    public void notificationCheck_CooldownDrugAlreadyFinished_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).DrugNotification();
    }

    @Test
    public void notificationCheck_CooldownDrugStillOn_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(125,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(105,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).DrugNotification();
    }




    /****************************************/
    /*                                      */
    /*            Medical Tests             */
    /*                                      */
    /****************************************/

    @Test
    public void notificationCheck_CooldownMedicalFinished_CooldownsNotificationCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,11,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).MedicalNotification();
    }

    @Test
    public void notificationCheck_CooldownMedicalFinishedSettingDisabled_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(false);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,11,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).MedicalNotification();
    }

    @Test
    public void notificationCheck_CooldownMedicalAlreadyFinished_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).MedicalNotification();
    }

    @Test
    public void notificationCheck_CooldownMedicalStillOn_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,125,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,105,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).MedicalNotification();
    }




    /****************************************/
    /*                                      */
    /*           Boosters Tests             */
    /*                                      */
    /****************************************/

    @Test
    public void notificationCheck_CooldownBoostersFinished_CooldownsNotificationCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,11));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(1)).BoosterNotification();
    }

    @Test
    public void notificationCheck_CooldownBoostersFinishedSettingDisabled_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(false);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,11));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).BoosterNotification();
    }

    @Test
    public void notificationCheck_CooldownBoostersAlreadyFinished_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,0));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).BoosterNotification();
    }

    @Test
    public void notificationCheck_CooldownBoostersStillOn_CooldownsNotificationNotCalled(){
        // Arrange
        when(sampleSaveData.CooldownNotification()).thenReturn(true);
        when(sampleLastUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,125));
        when(sampleNowUser.getCooldowns()).thenReturn(new TornUser.Cooldown(0,0,105));

        nothing(tnm);
        doCallRealMethod().when(tnm).notificationsCheck(any(SaveData.class), any(TornUser.class), any(TornUser.class));

        // Act
        tnm.notificationsCheck(sampleSaveData, sampleNowUser, sampleLastUser);

        // Assert
        verify(tnm, times(0)).BoosterNotification();
    }

}
