package com.chan.jx3_market.presenterImpl;

import android.support.v7.app.AlertDialog;

import com.chan.jx3_market.modelimpl.MainModelImpl;
import com.chan.jx3_market.presenter.IMainPresenter;
import com.chan.jx3_market.viewimpl.MainActivity;

/**
 * Created by qianlei on 2016-03-31.14:01
 * class description:
 */
public class MainPresenterImpl implements IMainPresenter {

    private static MainActivity activity;
    private static MainModelImpl model;
    private static volatile MainPresenterImpl presenter;

    private MainPresenterImpl() {
    }

    public static MainPresenterImpl getInstance(MainActivity ac){
        if(presenter == null){
            synchronized(MainPresenterImpl.class){
                if(presenter == null){
                    presenter = new MainPresenterImpl();
                    activity = ac;
                    model = new MainModelImpl(ac);
                }
            }
        }
        return presenter;
    }

    @Override
    public String getSearchContent() {
        return null;
    }

    @Override
    public void performSearchAllClickEvent() {

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
