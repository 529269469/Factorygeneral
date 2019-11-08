package com.example.factorygeneral.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factorygeneral.R;


/**
 * Created by lxz on 2018/5/18.
 */

public class ToastUtils {
    private static volatile ToastUtils setGrid;
    private Toast toast = null;
    private Toast mToast;
    private TextView textView;

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        if (setGrid == null) {
            synchronized (ToastUtils.class) {
                if (setGrid == null) {
                    setGrid = new ToastUtils();
                }
            }
        }
        return setGrid;
    }

    public void showTextToast(Context context, String msg) {

        if (mToast != null) {
            mToast.cancel();
        }

        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 200);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


}



