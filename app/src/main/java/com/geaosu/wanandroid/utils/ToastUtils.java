package com.geaosu.wanandroid.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geaosu.wanandroid.R;

public class ToastUtils {
    private static Toast mToast;
    private static ToastUtils mInstance;
    private static TextView mTvMsg;

    private ToastUtils() {
    }

    public synchronized static ToastUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ToastUtils();
        }

        if (mToast == null) {
            mToast = new Toast(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View toastView = inflater.inflate(R.layout.toast_layout, null);
            mTvMsg = toastView.findViewById(R.id.tvMsg);
            //mToast.setGravity(Gravity.CENTER, 0, 0);//显示在屏幕的正中间
            mToast.setView(toastView);
        }

        return mInstance;
    }

    public void showToast(String msg) {
        mTvMsg.setText(msg);
        mToast.show();
    }

    public void cancelToast() {
        mToast.cancel();
    }
}
