package com.app.qunadai;

import com.app.qunadai.bean.Message;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.google.gson.Gson;

import org.json.JSONObject;
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
        assertEquals("", m.getContent().getMobileNumber());
    }


    @Test
    public void testMoney() throws Exception {
        String s1 = "00034000";
        String s0 = "";
        assertEquals(true, CommUtil.isNumber(s0));
    }


    @Test
    public void parsejson() throws Exception {
        String str = "[{\"title\":\"去哪贷是什么性质的贷款平台？\",\"answer\":\"去哪贷是一家贷款需求智能匹配平台，本身不提供放款，只提供信息撮合和技术服务。我们不向用户收取任何费用。\"}" +
                ",{\"title\":\"如何申请贷款？贷款流程是什么样的？\",\"answer\":\"1、注册成为去哪贷会员。\n2、提交身份信息完成身份认证。\n3、在借款栏目选择贷款产品并提交借款申请。\n4、机构根据您的资质条件，会对您的贷款申请进行审核。\n5、审核通过机构放款，不通过会后台拒绝。\"}" +
                ",{\"title\":\"在去哪贷申请贷款需要费用吗？\",\"answer\":\"去哪贷完全免费，平台上的合作机构提供的贷款产品可能会收取部分手续费用。\"}" +
                ",{\"title\":\"贷款利息？\",\"answer\":\"不同的贷款产品有不同贷款利息，具体贷款利息见贷款产品详细页的描述。\"}" +
                ",{\"title\":\"贷款额度？\",\"answer\":\"贷款额度不同的贷款产品具有不同的贷款额度，不同的机构根据您提供的贷款资质会给出您不同的贷款额度。一般区间是在500-10万之间。\"}" +
                ",{\"title\":\"个人征信有不良记录能从去哪贷贷到款吗？\",\"answer\":\"去哪贷本身不做贷款资质审核，也不下款，所有的贷款产品都是通过入驻平台的贷款机构提供。所以无法明确告知是否能贷到款。具体事宜可以咨询入驻贷款机构。\"}" +
                ",{\"title\":\"我多长时间可以收到贷款？\",\"answer\":\"入驻的不同的贷款机构审核时间和下款时间都不一样，最快3-5分钟，最慢3-5天，平均时间基本都是当天或者隔天。\"}" +
                ",{\"title\":\"去哪贷可以提供哪些类型的贷款？\",\"answer\":\"去哪贷平台基本覆盖常见的贷款类型。具体可以通过贷款产品筛选页筛选符合您的贷款产品。\"}" +
                ",{\"title\":\"如果逾期不还会怎么样？\",\"answer\":\"如果发生逾期，去哪贷的贷款机构可能会产生一定的逾期费用，严重的可能会影响个人征信。具体逾期好借好还，再借不难，建议各位用户按时还款哦！\"}" +
                ",{\"title\":\"借钱的用途有限制吗？\",\"answer\":\"借钱的用途不作限制，只要到期能够还款即可。当然，我们鼓励正面的用途。\"}" +
                ",{\"title\":\"贷款需要什么条件？\",\"answer\":\"平台上的不同机构的贷款条件要求是不一样的。建议查看贷款产品详细页的说明。\"}" +
                ",{\"title\":\"我的个人信息会不会泄露？\",\"answer\":\"去哪贷平台有严格的数据保密机制，有专业的服务器和数据运维人员保障您的数据安全。我们承诺完全为对您提交的信息全程保密。\"}" +
                ",{\"title\":\"怎么样查看我的贷款进度？\",\"answer\":\"贷款申请进度，放款机构会随时更新最新进度并通知您的。您在申请后台既可查看。\"}" +
                ",{\"title\":\"贷款对地区有要求和限制吗？\",\"answer\":\"您所在的地区是否能申请贷款，请关注去哪贷平台上的贷款产品具体描述或者通过贷款产品筛选列表查看。\"}" +
                ",{\"title\":\"去哪贷都是无抵押贷款吗？\",\"answer\":\"去哪贷平台上有很多贷款机构和贷款产品，其中包含有很多无抵押贷款产品，不同的贷款产品对抵押物要求有所不同。\"}]";

        JSONObject obj = new JSONObject(str);
//        assertEquals("",obj.);
    }

    @Test
    public void getLastChar() throws Exception {
        String str = "30天";

        assertEquals("天", str.substring(str.length() - 1, str.length()));

    }

    @Test
    public void testAlphaSize() throws Exception {
        String[] alpha = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        assertEquals(26, alpha.length);
    }


    @Test
    public void getLastVersion() {
        String version = "4.0.2";
        String last = String.valueOf(version.charAt(version.length() - 1));
        assertEquals("2", last);
    }

}