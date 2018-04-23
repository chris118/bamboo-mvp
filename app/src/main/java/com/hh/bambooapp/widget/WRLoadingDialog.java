package com.hh.bambooapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hh.bambooapp.R;

/**
 * Created by xiaopeng on 2017/8/1.
 */

public class WRLoadingDialog extends Dialog {
    private ImageView progressImg;
    //帧动画
    private AnimationDrawable animation;

    public WRLoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_loading);

        //点击imageview外侧区域，动画不会消失
        setCanceledOnTouchOutside(false);

        progressImg = (ImageView) findViewById(R.id.refreshing_drawable_img);
        //加载动画资源
        animation = (AnimationDrawable) progressImg.getDrawable();
    }

    /**
     * 在AlertDialog的 onStart() 生命周期里面执行开始动画
     */
    @Override
    protected void onStart() {
        super.onStart();
        animation.start();
    }

    /**
     * 在AlertDialog的onStop()生命周期里面执行停止动画
     */
    @Override
    protected void onStop() {
        super.onStop();
        animation.stop();
    }

    static WRLoadingDialog dialog;
    public static void show(Context context){
        dialog = new WRLoadingDialog(context);
        if(dialog != null){
            dialog.show();
        }
    }

    public static void dismiss(Context context){
        if(dialog != null){
            dialog.dismiss();
        }
    }
}
