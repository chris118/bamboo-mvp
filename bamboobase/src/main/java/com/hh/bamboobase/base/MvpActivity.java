package com.hh.bamboobase.base;

import android.os.Bundle;

import androidx.annotation.CallSuper;

import com.hh.bamboobase.base.mvp.IPresenter;

/**
 * Created by chrisw on 16/8/5.
 * 需要使用MVP模式的Activity继承它
 */
public abstract class MvpActivity<P extends IPresenter> extends BaseActivity {

    protected P mPresenter;

    @CallSuper
    @Override
    protected void preInit(Bundle savedInstanceState) {
        super.preInit(savedInstanceState);
        mPresenter = createPresenter();
    }

    /**
     * 创建IPresenter 对象
     * @return
     */
    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
