package com.app.qunadai;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by wayne on 2017/5/19.
 */

public class InfoUnitTest {

    @Test
    public void testIdcard() throws Exception {
        String id = "31011319900948171X";
        String strPattern = "(^[0-9]{15}$)|([0-9]{17}([0-9]|X)$)";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(id);
        boolean result = m.matches();
        assertEquals(true,result);
    }
}
