package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.view.IMainActivity;

import base.BaseActivity;

/**
 * Created by qianlei on 2016-03-30.11:47
 * class description:
 */
public class MainActivity extends BaseActivity implements IMainActivity {

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public static void jumpToMainActivity(Activity ac){
        Intent intent = new Intent(ac,MainActivity.class);
        ac.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void handleSearchEvent() {

    }
}
