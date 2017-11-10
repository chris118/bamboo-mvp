package com.hh.bamboobase.rx;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

    private static RxBus sBus;
    private Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    public static synchronized RxBus getDefaultBus() {
        if (sBus == null) {
            sBus = new RxBus();
        }
        return sBus;
    }

    public static boolean hasObserver() {
        return getDefaultBus().subject.hasObservers();
    }

    public static void publish(Object event) {
        getDefaultBus().subject.onNext(event);
    }

    public static Observable<Object> toObserve() {
        return getDefaultBus().subject.asObservable().observeOn(AndroidSchedulers.mainThread());
    }

    public static Subscription subscribe(Action1<Object> action1){
        return RxBus.toObserve().subscribe(action1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        });
    }
}
