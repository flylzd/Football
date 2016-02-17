package com.derby.football.widget;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.derby.football.R;

public class LoadingDialog  {

    private Context context;
    private Dialog dialog;
    private TextView tvLoadingMsg;

    public LoadingDialog(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        dialog = new Dialog(context,R.style.loadingDialogStyle);
        dialog.getWindow().setWindowAnimations(R.style.loadingDialogAnimStyle);
        dialog.setContentView(R.layout.dialog_loading);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);

        tvLoadingMsg = (TextView)dialog.findViewById(R.id.tvLoadingMsg);
        tvLoadingMsg.setText("正在加载...");
    }

    public void setMessage(CharSequence msg) {
        this.tvLoadingMsg.setText(msg);
    }

    public void setMessage(int resId) {
        this.tvLoadingMsg.setText(resId);
    }

    public void show() {
        dialog.show();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    /**
     * 提示栏被取消
     * @param onCancelListener
     */
    public void setCancleListener(DialogInterface.OnCancelListener onCancelListener) {
        dialog.setOnCancelListener(onCancelListener);
    }

}
