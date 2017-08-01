package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.FindCity;
import com.app.qunadai.content.contract.FindCityContract;
import com.app.qunadai.content.model.FindCityModelImpl;

/**
 * Created by wayne on 2017/8/1.
 */

public class FindCityPresenter implements FindCityContract.Presenter {

    private FindCityContract.View view;
    private FindCityContract.Model model;

    public FindCityPresenter(FindCityContract.View iview) {
        this.view = iview;
        model = new FindCityModelImpl(new FindCityModelImpl.OnReturnDataListener() {
            @Override
            public void findCity(FindCity bean) {
                view.findCity(bean);
            }

            @Override
            public void findCityFail(String error) {
                view.findCityError(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void findCity(String city) {
        model.findCity(city);
    }
}
