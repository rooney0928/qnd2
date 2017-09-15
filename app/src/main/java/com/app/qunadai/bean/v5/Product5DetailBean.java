package com.app.qunadai.bean.v5;

/**
 * Created by wayne on 2017/9/14.
 */

public class Product5DetailBean {


    /**
     * product : {"id":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","status":"NORMAL","provider":{"id":"4d9f4749-5e11-415c-9161-a76188df33e5","status":null,"name":"钱师爷","type":"理财","address":"上海市","code":null,"contactsPerson":"网二","contactsPhoneNumber":"18644112255","icon":null,"lppStatus":"ENABLED"},"productNo":"25","name":"U族大学贷2","describe":"是一款最最划算的大学生贷款软件平台，如果您短暂缺乏资金就可以使用这款软禁进行无息贷款","type":"H5","icon":"10aecbcf-278c-4452-9e65-72b138c86fe7","amount":"5000-10000","minAmount":0,"maxAmount":10000,"term":"10-40天","minTerm":10,"maxTerm":100,"termUnit":"月","applicationConditions":"1.满18周岁姓名\n2.身份证号验证即可申请","applicationMaterials":"身份证，联系人信息","url":"https://m.u-zu.com/?channel=u-qnd-llcs","num":486,"rate":"0.73%-5%","loanTime":4,"loanTimeUnit":"SECONDS","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.73,"maxRate":5,"rateStatus":"MONTH","sucRate":80,"pOrder":1,"balanceRatio":"4","totalStarNumber":21,"totalCommentNumber":6}
     */

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
