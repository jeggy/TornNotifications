package net.jebster.TornNotifications;

import junit.framework.Test;
import junit.framework.TestSuite;

import net.jebster.TornNotifications.tools.TornNotificationManager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by jeggy on 10/4/16.
 */

@RunWith(Suite.class)

@Suite.SuiteClasses(
        {
                TornNotificationManagerTest.class,
                TimeUtilsTest.class
        }
)
public class AllTests {}
