package com.hh.bamboobase.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.util.SparseArray;

import androidx.core.app.NotificationCompat;

@SuppressLint("NewApi")
public class NotifyUtil {

    private int NOTIFICATION_ID;
    private NotificationManager nm;
    private Notification notification;
    private NotificationCompat.Builder cBuilder;
    private Context mContext;

    public static class NotifyCount {

        public static NotifyCount getInstance() {
            return Single.single;
        }

        private SparseArray<Integer> mNotifyCountArray;

        private NotifyCount() {
            mNotifyCountArray = new SparseArray<Integer>();
        }

        private static class Single {
            private static final NotifyCount single = new NotifyCount();
        }

        public void add(int sessionId) {
            Integer integer = mNotifyCountArray.get(sessionId);
            if (integer != null) {
                mNotifyCountArray.put(sessionId, integer + 1);
            } else {
                mNotifyCountArray.put(sessionId, 1);
            }
        }

        public int getCount(int sessionId) {
            Integer integer = mNotifyCountArray.get(sessionId);
            if (integer == null) {
                return 0;
            } else {
                return integer;
            }
        }

        public void clear() {
            if (mNotifyCountArray.size() != 0) {
                mNotifyCountArray.clear();
            }
        }
    }

    public NotifyUtil(Context context, int ID) {
        this.NOTIFICATION_ID = ID;
        mContext = context;
        // 获取系统服务来初始化对象
        nm = (NotificationManager) mContext
                .getSystemService(Activity.NOTIFICATION_SERVICE);
        cBuilder = new NotificationCompat.Builder(mContext);
    }

    /**
     * 设置在顶部通知栏中的各种信息
     *
     * @param pendingIntent
     * @param ticker
     */
    private void setCompatBuilder(PendingIntent pendingIntent, String ticker, String title, int smallIcon) {
        cBuilder.setContentIntent(pendingIntent);// 该通知要启动的Intent
        cBuilder.setSmallIcon(smallIcon);// 设置顶部状态栏的小图标
        if (ticker != null) {
            cBuilder.setTicker(getEmotionContent(ticker));// 在顶部状态栏中的提示信息
        }
        NotifyCount.getInstance().add(NOTIFICATION_ID);
        StringBuilder builder = new StringBuilder("[");
        builder.append(NotifyCount.getInstance().getCount(NOTIFICATION_ID)).append("条]").append(ticker);
        cBuilder.setContentTitle(title);// 设置通知中心的标题
        cBuilder.setContentText(getEmotionContent(builder.toString()));//设置通知中心中的内容
        cBuilder.setWhen(System.currentTimeMillis());

		/*
         * 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失,
		 * 不设置的话点击消息后也不清除，但可以滑动删除
		 */
        cBuilder.setAutoCancel(true);
        // 将Ongoing设为true 那么notification将不能滑动删除
        // notifyBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
		 * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
		 */
        cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        /*
         * Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
		 * Notification.DEFAULT_SOUND：系统默认铃声。
		 * Notification.DEFAULT_VIBRATE：系统默认震动。
		 * Notification.DEFAULT_LIGHTS：系统默认闪光。
		 * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
		 */
        int defaults = 0;
        defaults |= Notification.DEFAULT_SOUND;
        defaults |= Notification.DEFAULT_VIBRATE;
        cBuilder.setDefaults(defaults);
    }

    private String getEmotionContent(String source) {
        return source.replace("[url]", "").replace("[/url]", "");
    }

    /**
     * 普通的通知
     * <p/>
     * 1. 侧滑即消失，下拉通知菜单则在通知菜单显示
     *
     * @param pendingIntent
     * @param ticker
     * @param title
     */
    public void notify_normal_singline(PendingIntent pendingIntent, String ticker, String title, int smallIcon) {
        setCompatBuilder(pendingIntent, ticker, title, smallIcon);
        sent();
    }


    private void sent() {
        notification = cBuilder.build();
        // 发送该通知
        nm.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 根据id清除通知
     */
    public void clear() {
        // 取消通知
        nm.cancelAll();
        NotifyUtil.NotifyCount.getInstance().clear();
    }
}
