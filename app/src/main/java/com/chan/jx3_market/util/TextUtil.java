package com.chan.jx3_market.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by channey on 2017/4/7.
 */

public class TextUtil {
    public static boolean isEmpty(String s){
        if(TextUtils.isEmpty(s)){
            return true;
        }
        if("null".equalsIgnoreCase(s)){
            return true;
        }
        return false;
    }
}
