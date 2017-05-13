package com.app.qunadai;

import com.app.qunadai.bean.Message;
import com.app.qunadai.utils.CommUtil;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testGson() throws Exception {
//        assertEquals(4, 2 + 2);

        String json = "{\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"code\": \"000\",\n" +
                "    \"detail\": \"短信已发送\",\n" +
                "    \"content\": {\n" +
                "    \"userId\": \"e8131be4-5afd-4b91-b400-de8a170ff591\"\n" +
                "    }\n" +
                "    }";

        Message m = new Gson().fromJson(json, Message.class);
        assertEquals("",m.getContent().getMobileNumber());
    }


    @Test
    public void testMoney ()throws Exception{
        String s1 = "00034000";
        String s0 = "";
        assertEquals(true, CommUtil.isNumber(s0));
    }



}