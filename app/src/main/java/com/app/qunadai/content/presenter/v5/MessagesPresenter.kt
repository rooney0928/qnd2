package com.app.qunadai.content.presenter.v5

import com.app.qunadai.bean.base.BaseBean
import com.app.qunadai.bean.v5.ReplyMessages
import com.app.qunadai.content.contract.v5.MessagesContract
import com.app.qunadai.content.model.v5.MessagesModelImpl

/**
 * Created by wayne on 2017/11/6.
 */

class MessagesPresenter(private val view: MessagesContract.View) : MessagesContract.Presenter {


    private val model: MessagesContract.Model

    init {
        model = MessagesModelImpl(object : MessagesModelImpl.OnReturnDataListener {
            override fun getMessagesMore(bean: BaseBean<ReplyMessages>?) {
                view.getMessagesMore(bean)
            }

            override fun tokenFail() {
                view.tokenFail()
            }

            override fun getMessages(bean: BaseBean<ReplyMessages>?) {
                view.getMessages(bean)
            }

            override fun getMessagesFail(error: String?) {
                view.getMessagesFail(error)
            }

            override fun requestStart() {
                view.requestStart()
            }

            override fun requestEnd() {
                view.requestEnd()
            }
        })
    }

    override fun getServerData() {

    }

    override fun getMessages(token: String?, page: Int, size: Int) {
        model.getMessages(token, page, size)
    }

}