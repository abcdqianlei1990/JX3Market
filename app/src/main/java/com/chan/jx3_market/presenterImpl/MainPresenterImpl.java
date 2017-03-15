package com.chan.jx3_market.presenterImpl;

import com.chan.jx3_market.modelimpl.MainModelImpl;
import com.chan.jx3_market.presenter.IMainPresenter;
import com.chan.jx3_market.viewimpl.MainActivity;

import com.chan.jx3_market.constants.Constants;

/**
 * Created by qianlei on 2016-03-31.14:01
 * class description:
 */
public class MainPresenterImpl implements IMainPresenter {

    private  MainActivity activity;
    private  MainModelImpl model;

    public MainPresenterImpl(MainActivity activity) {
        this.activity = activity;
        model = new MainModelImpl(activity,this);
    }

    @Override
    public String getSearchContent() {
        return null;
    }

    @Override
    public void performSearchAllClickEvent(int type) {
        switch (type){
            case Constants.PUBLISH_INFO_TYPE_ACCOUNT:
                break;
            case Constants.PUBLISH_INFO_TYPE_GOLD:
                break;
            case Constants.PUBLISH_INFO_TYPE_SERVICE:
                break;
            case Constants.PUBLISH_INFO_TYPE_OTHER:
                break;
        }
    }

    @Override
    public void performPublishClickEvent() {

    }

    @Override
    public void performSearchMyClickEvent() {

    }

    @Override
    public void performAboutClickEvent() {

    }
}
