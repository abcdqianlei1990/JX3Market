package com.chan.jx3_market.modelimpl;

import com.chan.jx3_market.model.IMainModel;
import com.chan.jx3_market.presenterImpl.MainPresenterImpl;
import com.chan.jx3_market.viewimpl.MainActivity;

/**
 * Created by qianlei on 2016-03-31.14:03
 * class description:
 */
public class MainModelImpl implements IMainModel {

    private MainActivity activity;
    MainPresenterImpl presenter;

    public MainModelImpl(MainActivity activity) {
        this.activity = activity;
        presenter = MainPresenterImpl.getInstance(activity);
    }
}
