package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chan.jx3_market.R;

import com.chan.jx3_market.base.BaseActivity;

/**
 * Created by qianlei on 2016-03-31.20:49
 * class description:
 */
public class AboutActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,AboutActivity.class);
        activity.startActivity(intent);
    }
}
