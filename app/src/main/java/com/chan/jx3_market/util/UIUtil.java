package com.chan.jx3_market.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by channey on 2016/11/1.
 * version:1.0
 * desc:
 */

public class UIUtil {

    public static int dip2px(Context context, int dip){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int dpi = dm.densityDpi;
        return dip*(dpi/160);
    }

    public static int px2dip(Context context, int px){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        int dpi = dm.densityDpi;
        return (px*160)/dpi;
    }

    /**
     * 修改textview中局部文字的颜色
     * 如修改"1234"中 3 的颜色为红色
     * changeColorInTv("1234",Color.Red,2,3,tv)
     * @param string 显示的内容
     * @param color 想要改变的颜色
     * @param startIndex 改变文字的起始下标
     * @param endIndex 改变文字的结束下标
     * @param textView 目标textview
     * @return SpannableStringBuilder
     */
    public static void changeColorInTv(String string, int color, int startIndex, int endIndex, TextView textView){
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        builder.setSpan(span,startIndex,endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 获取手机密度
     * @param context
     * @return
     */
    public static float getDensity(AppCompatActivity context){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.density;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 按钮点击缩放动画
     * @param target
     */
    public static void performClickAnimator(Object target){
        ObjectAnimator animator1 = ObjectAnimator.
                ofFloat(target, "scaleX", 1.0f, 0.7f,1.0f);
        ObjectAnimator animator2 = ObjectAnimator.
                ofFloat(target, "scaleY", 1.0f, 0.7f,1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1);
        set.play(animator2);
        set.setDuration(500);
        set.start();
    }
}
