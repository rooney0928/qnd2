package com.app.qunadai.content.presenter.cpl

import com.app.qunadai.bean.base.CplBase
import com.app.qunadai.bean.cpl.CToken
import com.app.qunadai.content.contract.cpl.AuthInfoContract
import com.app.qunadai.content.model.cpl.AuthInfoModelImpl

/**
 * Created by wayne on 2017/11/8.
 */

class AuthInfoPresenter(private val view: AuthInfoContract.View) : AuthInfoContract.Presenter {
    private val model: AuthInfoContract.Model

    init {
        this.model = AuthInfoModelImpl(object : AuthInfoModelImpl.OnReturnDataListener {
            override fun getCplToken(bean: CplBase<CToken>) {
                view.getCplToken(bean)
            }

            override fun getCplTokenFail(error: String) {
                view.getCplTokenFail(error)
            }

            override fun requestStart() {
                view.requestStart()
            }

            override fun requestEnd() {
                view.requestEnd()
            }

            override fun tokenFail() {
                view.tokenFail()
            }
        })
    }

    override fun getServerData() {

    }

    override fun reqCplToken(phone: String) {
        model.reqCplToken(phone)
    }
}
