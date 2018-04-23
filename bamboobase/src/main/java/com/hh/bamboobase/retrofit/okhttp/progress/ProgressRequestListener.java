package com.hh.bamboobase.retrofit.okhttp.progress;

/**
 * 请求体进度回调接口，比如用于文件上传中
 * Created by chrisw on 2015/10/26.
 */
public interface ProgressRequestListener {

    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
