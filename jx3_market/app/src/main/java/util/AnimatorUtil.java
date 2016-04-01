package util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

/**
 * Created by qianlei on 2016-04-01.17:15
 * class description:动画工具类
 */
public class AnimatorUtil {

    /**
     * 按钮点击缩放动画
     * @param target
     */
    public static void performClickAnimator(Object target){
        ObjectAnimator animator1 = ObjectAnimator.
                ofFloat(target, "scaleX", 1.0f, 0.7f);
        ObjectAnimator animator2 = ObjectAnimator.
                ofFloat(target, "scaleX", 0.7f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1);
        set.play(animator2);
        set.setDuration(500);
        set.start();
    }
}
