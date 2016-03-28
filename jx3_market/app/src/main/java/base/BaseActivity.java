package base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by qianlei on 2016-03-28.16:43
 * class description:
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg) {
        if(!"非法用户".equals(toastMsg)){
            showToast(toastMsg, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg, int duration) {
        Toast.makeText(this, toastMsg, duration).show();
    }
}
