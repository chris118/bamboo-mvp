package com.hh.bamboobase.utils;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chrisw on 2017/5/5.
 */

public class RxHelper {

    public static Subscription clicks(View view, Action1<Void> action1){
        return RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    public static Subscription longClicks(View view, Action1<Void> action1){
       return RxView.longClicks(view).observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

   public static Subscription timer(long time, Action1<Long> action1){
       return Observable.timer(time, TimeUnit.MILLISECONDS).
               subscribe(action1, new Action1<Throwable>() {
           @Override
           public void call(Throwable throwable) {
           }
       });
   }

   public static <T extends Just, P
            extends Result> Subscription justIoToMain(T object, Func1<T, P> func, Action1<P> action){
      return Observable.just(object).subscribeOn(Schedulers.io())
               .map(func)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(action, new Action1<Throwable>() {
                   @Override
                   public void call(Throwable throwable) {
                   }
               });
   }


   public static class Just{
   }

    public static class Result{

    }
}
