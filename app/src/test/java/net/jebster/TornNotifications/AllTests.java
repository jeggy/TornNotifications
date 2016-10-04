package net.jebster.TornNotifications;

import net.jebster.TornNotifications.notifications.NotificationSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by jeggy on 10/4/16.
 */

@RunWith(Suite.class)

@Suite.SuiteClasses(
        {
                NotificationSuite.class,
                TimeUtilsTest.class
        }
)

public class AllTests {}
