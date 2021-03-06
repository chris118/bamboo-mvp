package com.hh.bamboobase.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.hh.bamboobase.base.mvp.IPresenter;

/**
 * Created by Administrator on 2016/9/2.
 * 需要使用MVP模式的Fragment继承它
 */
public abstract class MvpFragment<P extends IPresenter> extends BaseFragment {

    protected P mPresenter;

    /**
     * 创建IPresenter 对象
     * @return
     */
    protected abstract P createPresenter();

    @Override
    protected void preInit(@Nullable Bundle savedInstanceState) {
        super.preInit(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null){
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
