package net.jebster.TornNotifications;

import net.jebster.TornNotifications.Exceptions.NegativeNumberException;
import net.jebster.TornNotifications.tools.TimeUtils;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by jeggy on 10/4/16.
 */

public class TimeUtilsTest {

    @Test
    public void getStringTimeTest(){
        long test = 10379;

        String result = TimeUtils.getStringTime(test);

        Assert.assertEquals(result, "02:52:59");
    }

    @Test(expected = NegativeNumberException.class)
    public void getStringTime_ShouldFail(){
        long test = -5000;
        String result = TimeUtils.getStringTime(test);
    }

}
