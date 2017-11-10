package com.hh.bamboobase.widget.xrecyclerview;

/**
 * Created by jianghejie on 15/11/22.
 */
interface BaseRefreshHeader {

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplate();

    /**
     * 正常状态
     */
    public final static int STATE_NORMAL = 0;
    /**
     * 释放立即刷新
     */
    public final static int STATE_RELEASE_TO_REFRESH = 1;
    /**
     * 正在刷新
     */
    public final static int STATE_REFRESHING = 2;
    /**
     * 刷新完成
     */
    public final static int STATE_DONE = 3;
}
