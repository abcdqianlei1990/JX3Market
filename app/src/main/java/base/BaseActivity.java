package base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chan.jx3_market.R;

/**
 * Created by qianlei on 2016-03-28.16:43
 * class description:
 */
public class BaseActivity extends AppCompatActivity {

    public JX3Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = JX3Application.getInstance();
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(View view,String toastMsg) {
        if(!"非法用户".equals(toastMsg)){
            showToast(view,toastMsg, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    private void showToast(View view,String toastMsg, int duration) {
//        Toast.makeText(this, toastMsg, duration).show();
        Snackbar snackbar = Snackbar.make(view, toastMsg, duration);
        View v = snackbar.getView();
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    public interface RecyclerViewItemClickListener{
        public void onItemClick(View v,int position);
    }

}
