package com.app.qunadai.bean.v5

/**
 * Created by wayne on 2017/11/7.
 */

class ReplyMessages {

    /**
     * replied_comments : {"content":[{"replies":[{"commentId":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","content":"new one","createdTime":1509699209000,"id":"3f1eedb2-422a-4642-bac2-872d01985389","replyNumber":0,"updatedTime":1509699209000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d","usernick":"good_dog"}],"content":"希望这次给我通过吧","createdTime":1508358654000,"id":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","productIcon":"103ac7fe-cad6-4057-b8b7-0f5715335c75","productId":"6b397c6f-fb3d-486d-a970-08644ad249ad","productName":"我来贷","replyNumber":2,"updatedTime":1509699209000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d"},{"replies":[{"commentId":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","content":"hello","createdTime":1509693916000,"id":"8349a1e4-42fc-4662-9f35-8347ebaed59a","replyNumber":0,"updatedTime":1509693916000,"userId":"2fdc8480-10f8-4933-bac8-bb4800b29b25","usernick":"13000111222"}],"content":"真的应急。太感谢了","createdTime":1508342322000,"id":"019d4c6f-39df-4d40-af95-d5776cd37c86","productIcon":"25c06f1d-d177-48d9-988c-4c416361b97a","productId":"2a028d3f-ae87-4472-b8dd-ebb34bc77b17","productName":"魔法现金","replyNumber":2,"updatedTime":1509690937000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":5,"sort":1,"totalElements":2,"totalPages":1}
     */

    var replied_comments: RepliedCommentsBean? = null

    class RepliedCommentsBean {
        /**
         * content : [{"replies":[{"commentId":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","content":"new one","createdTime":1509699209000,"id":"3f1eedb2-422a-4642-bac2-872d01985389","replyNumber":0,"updatedTime":1509699209000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d","usernick":"good_dog"}],"content":"希望这次给我通过吧","createdTime":1508358654000,"id":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","productIcon":"103ac7fe-cad6-4057-b8b7-0f5715335c75","productId":"6b397c6f-fb3d-486d-a970-08644ad249ad","productName":"我来贷","replyNumber":2,"updatedTime":1509699209000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d"},{"replies":[{"commentId":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","content":"hello","createdTime":1509693916000,"id":"8349a1e4-42fc-4662-9f35-8347ebaed59a","replyNumber":0,"updatedTime":1509693916000,"userId":"2fdc8480-10f8-4933-bac8-bb4800b29b25","usernick":"13000111222"}],"content":"真的应急。太感谢了","createdTime":1508342322000,"id":"019d4c6f-39df-4d40-af95-d5776cd37c86","productIcon":"25c06f1d-d177-48d9-988c-4c416361b97a","productId":"2a028d3f-ae87-4472-b8dd-ebb34bc77b17","productName":"魔法现金","replyNumber":2,"updatedTime":1509690937000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d"}]
         * first : true
         * firstPage : false
         * last : true
         * lastPage : false
         * nextPage : false
         * number : 0
         * numberOfElements : 2
         * previousPage : false
         * size : 5
         * sort : 1
         * totalElements : 2
         * totalPages : 1
         */

        var isFirst: Boolean = false
        var isFirstPage: Boolean = false
        var isLast: Boolean = false
        var isLastPage: Boolean = false
        var isNextPage: Boolean = false
        var number: Int = 0
        var numberOfElements: Int = 0
        var isPreviousPage: Boolean = false
        var size: Int = 0
        var sort: Int = 0
        var totalElements: Int = 0
        var totalPages: Int = 0
        var content: List<ContentBean>? = null

        class ContentBean {
            /**
             * replies : [{"commentId":"02a42ef6-e10a-4c88-96f2-62d7b953e87f","content":"new one","createdTime":1509699209000,"id":"3f1eedb2-422a-4642-bac2-872d01985389","replyNumber":0,"updatedTime":1509699209000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d","usernick":"good_dog"}]
             * content : 希望这次给我通过吧
             * createdTime : 1508358654000
             * id : 02a42ef6-e10a-4c88-96f2-62d7b953e87f
             * productIcon : 103ac7fe-cad6-4057-b8b7-0f5715335c75
             * productId : 6b397c6f-fb3d-486d-a970-08644ad249ad
             * productName : 我来贷
             * replyNumber : 2
             * updatedTime : 1509699209000
             * userId : 1df346bd-9f3c-4c9f-ae31-95e11fe6126d
             */

            var content: String? = null
            var createdTime: Long = 0
            var id: String? = null
            var productIcon: String? = null
            var productId: String? = null
            var productName: String? = null
            var replyNumber: Int = 0
            var updatedTime: Long = 0
            var userId: String? = null
            var replies: List<RepliesBean>? = null

            class RepliesBean {
                /**
                 * commentId : 02a42ef6-e10a-4c88-96f2-62d7b953e87f
                 * content : new one
                 * createdTime : 1509699209000
                 * id : 3f1eedb2-422a-4642-bac2-872d01985389
                 * replyNumber : 0
                 * updatedTime : 1509699209000
                 * userId : 1df346bd-9f3c-4c9f-ae31-95e11fe6126d
                 * usernick : good_dog
                 */

                var commentId: String? = null
                var content: String? = null
                var createdTime: Long = 0
                var id: String? = null
                var replyNumber: Int = 0
                var updatedTime: Long = 0
                var userId: String? = null
                var usernick: String? = null
            }
        }
    }
}
