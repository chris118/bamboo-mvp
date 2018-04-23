package com.hh.bamboobase.base.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chrisw on 16/8/19.
 */
public abstract class BasePresenter<V extends IView, M extends IModel>  implements IPresenter{

    protected V mView;
    protected M mModel;

    private CompositeSubscription mCompositeSubscription;
    private Subscription mSubscription;

    public BasePresenter(V view) {
        this.mView = view;
        mModel = createModel();
    }

    protected abstract M createModel();

    @Override
    public void onDestroy() {
        this.mModel = null;
        this.mView = null;
        unsubscribeEvents();
        unSubscribe();
    }

    protected void onNext(Object event) {
    }

    protected void unsubscribeEvents() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void addSubscribe(Subscription subscription) {
        if (subscription == null) return;
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }
}
