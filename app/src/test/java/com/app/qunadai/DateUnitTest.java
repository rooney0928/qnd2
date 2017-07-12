package com.app.qunadai;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by wayne on 2017/5/19.
 */

public class DateUnitTest {

    private long one_hour;

    @Test
    public void testIdcard() throws Exception {
        String id = "31011319900948171X";
        String strPattern = "(^[0-9]{15}$)|([0-9]{17}([0-9]|X)$)";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(id);
        boolean result = m.matches();
        assertEquals(true,result);
        
    }
    @Test
    public void testDate() throws Exception {
        long ONE_MINUTE = 60000L;
        long one_hour = 3600000L;

        long time = 45L * ONE_MINUTE;
        assertEquals(4L,time);

    }

}
