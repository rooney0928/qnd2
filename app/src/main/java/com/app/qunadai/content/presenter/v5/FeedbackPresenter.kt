package com.app.qunadai.content.presenter.v5

import com.app.qunadai.bean.base.BaseBean
import com.app.qunadai.bean.v5.FeedBack
import com.app.qunadai.content.contract.v5.FeedbackContract
import com.app.qunadai.content.model.v5.FeedbackModelImpl

/**
 * Created by wayne on 2017/11/6.
 */

class FeedbackPresenter(private val view: FeedbackContract.View) : FeedbackContract.Presenter {
    private val model: FeedbackContract.Model

    init {
        model = FeedbackModelImpl(object : FeedbackModelImpl.OnReturnDataListener {
            override fun addFeedback(bean: BaseBean<FeedBack>) {
                view.addFeedback(bean)
            }

            override fun addFeedbackFail(error: String) {
                view.addFeedbackFail(error)
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

    override fun addFeedback(token: String, msg: String) {
        model.addFeedback(token, msg)
    }
}
