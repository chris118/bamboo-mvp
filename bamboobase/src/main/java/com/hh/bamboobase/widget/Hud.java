package com.hh.bamboobase.widget;

import android.app.Application;
import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by chrisw on 2017/9/21.
 */

public class Hud {
    private static KProgressHUD hud;

    public static void show(Context context){
        if(hud == null){
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        }
        hud.show();
    }

    public static void dissmiss(){
        if(hud != null){
            hud.dismiss();
        }
    }

}
