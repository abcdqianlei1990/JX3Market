package com.chan.jx3_market.viewimpl;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.chan.jx3_market.R;
import com.chan.jx3_market.presenter.IMainPresenter;
import com.chan.jx3_market.presenterImpl.MainPresenterImpl;
import com.chan.jx3_market.view.IMainActivity;

import base.BaseActivity;
import constants.Keys;
import tyrantgit.explosionfield.ExplosionField;
import util.AnimatorUtil;

/**
 * Created by qianlei on 2016-03-30.11:47
 * class description:主页
 */
public class MainActivity extends BaseActivity implements IMainActivity,View.OnClickListener,View.OnTouchListener{

    private SearchView mSearchView;
    private CardView mSearchAll;
    private CardView mPublish;
    private CardView mSearchMy;
    private CardView mAbout;
    private FloatingActionButton mFab;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private IMainPresenter mPresenter;

    private ExplosionField mExplosionField;

    int pointX;
    int pointY;
    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;

    private static final int ACTION_CODE_SEARCH = 0;
    private static final int ACTION_CODE_PUB = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenterImpl(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;

        initVies();
        mExplosionField = ExplosionField.attach2Window(this);

    }

    public void initVies(){
        setContentView(R.layout.activity_main);
        mSearchView = (SearchView) findViewById(R.id.main_search);
        mSearchAll = (CardView) findViewById(R.id.main_search_all);
        mPublish= (CardView) findViewById(R.id.main_publish);
        mSearchMy = (CardView) findViewById(R.id.main_search_my);
        mAbout = (CardView) findViewById(R.id.main_about_author);
        mFab = (FloatingActionButton) findViewById(R.id.main_fab);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);

        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_item_one:
                        Log.d("qianlei", "===>navigation_item_one");
                        item.setChecked(true);
                        break;
                    case R.id.navigation_item_two:
                        Log.d("qianlei", "===>navigation_item_two");
                        item.setChecked(true);
                        break;
                    case R.id.navigation_item_three:
                        Log.d("qianlei", "===>navigation_item_three");
                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });

        mSearchAll.setOnClickListener(this);
        mPublish.setOnClickListener(this);
        mSearchMy.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mFab.setOnClickListener(this);
        mFab.setOnTouchListener(this);


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
    public String getSearchContent() {
        return null;
    }

    @Override
    public void handleSearchEvent() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.main_search:

                break;
            case R.id.main_search_all:
                AnimatorUtil.performClickAnimator(mSearchAll);
//                mExplosionField.explode(v);
                showPubTypeSelectDialog(ACTION_CODE_SEARCH);
                break;
            case R.id.main_publish:
                AnimatorUtil.performClickAnimator(mPublish);
                showPubTypeSelectDialog(ACTION_CODE_PUB);
                break;
            case R.id.main_search_my:
                AnimatorUtil.performClickAnimator(mSearchMy);
//                mExplosionField.explode(v);
                //如果未发布任何信息，则做出提示
                mPresenter.performSearchMyClickEvent();
                break;
            case R.id.main_about_author:
                AnimatorUtil.performClickAnimator(mAbout);
//                mExplosionField.explode(v);
//                mPresenter.performAboutClickEvent();
                AboutActivity.jumpToThisActivity(this);
                break;
        }
    }


    public void showPubTypeSelectDialog(final int action){
        final RadioGroup rg;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_pub_type_select_dialog,null);
        rg = (RadioGroup) view.findViewById(R.id.pub_type_select_dialog_radiogroup);

        builder.setView(view);
        if(ACTION_CODE_PUB == action){
            builder.setTitle("发布信息类型选择");
        }else{
            builder.setTitle("查询信息类型选择");
        }
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到对应页面
                int checkedId = rg.getCheckedRadioButtonId();
                switch (checkedId){
                    case R.id.pub_type_select_dialog_account:
//                        showToast(mPublish,"跳转到账号发布页面");
                        if(ACTION_CODE_PUB == action){
                            AccountInfoPubActivity.jumpToThisActivity(MainActivity.this);
                        }else{
//                            mPresenter.performSearchAllClickEvent(Keys.PUBLISH_INFO_TYPE_ACCOUNT);
                            AccountInfoListActivity.jumpToThisActivity(MainActivity.this);
                        }
                        break;
                    case R.id.pub_type_select_dialog_money:
                        showToast(mPublish,"跳转到金币发布页面");
                        break;
                    case R.id.pub_type_select_dialog_service:
                        showToast(mPublish,"跳转到代练代打发布页面");
                        break;
                    case R.id.pub_type_select_dialog_other:
                        showToast(mPublish,"跳转到其他信息发布页面");
                        break;
                    default:break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                pointX = (int) event.getRawX();
                pointY = (int) event.getRawY();
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + v.getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                int x =(int)event.getRawX();
                int y =(int)event.getRawY();
                int distanceX = Math.abs(x - pointX);
                int distanceY = Math.abs(y - pointY);
                Log.d("qianlei", "Y轴移动：" + distanceX + "|Y轴移动：" + distanceY);
                //如果横轴、纵轴移动距离都小于10，那么判断为点击事件，否则不触发点击事件
                if(distanceX <= 10 && distanceY <= 10){
                    fabClickAction();
                }
                pointX = x;
                pointY = y;
                break;
        }
        return false;
    }

    public void fabClickAction(){
        AnimatorUtil.performClickAnimator(mFab);
        boolean b = mDrawerLayout.isDrawerOpen(Gravity.LEFT);
        Log.d("qianlei", "===>b:" + b);
        if(b){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
