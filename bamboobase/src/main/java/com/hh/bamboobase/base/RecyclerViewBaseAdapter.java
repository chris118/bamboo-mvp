package com.hh.bamboobase.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by chrisw on 2016/3/19.
 */
public abstract class RecyclerViewBaseAdapter<T extends Object> extends RecyclerView.Adapter<BaseViewHolderHelper> {

    protected List<T> mData = new ArrayList<T>();
    protected Context mContext;
    private LayoutInflater inflater;

    public RecyclerViewBaseAdapter(List<T> data) {
        if (data != null) {
            mData.addAll(data);
        }
    }

    public RecyclerViewBaseAdapter() {
        this(null);
    }

    @LayoutRes
    public abstract int layoutResId(int viewType);

    @Override
    public final BaseViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
            inflater = LayoutInflater.from(mContext);
        }
        BaseViewHolderHelper helper = new BaseViewHolderHelper(inflater.inflate(layoutResId(viewType), parent, false));
        helper.setViewType(viewType);
        return helper;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<T> list) {
        if (mData.size() > 0) {
            mData.clear();
        }
        if (list != null) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取指定项对象
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 适配器数据是否包含某一项
     *
     * @param t
     * @return
     */
    public boolean isContains(@NonNull T t) {
        if (t != null) {
            return mData.contains(t);
        } else {
            return false;
        }
    }

    /**
     * 添加一个集合
     *
     * @param list
     */
    public void addAll(@NonNull List<T> list) {
        if (list != null) {
            mData.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一项到指定位置
     *
     * @param position
     * @param t
     */
    public void add(int position, @NonNull T t) {
        if (position < 0 || position > getItemCount() || t == null) {
            return;
        }
        mData.add(position, t);
        notifyDataSetChanged();
    }

    /**
     * 添加一项
     *
     * @param t
     */
    public void add(@NonNull T t) {
        if (t != null) {
            mData.add(t);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 移除指定的一项
     *
     * @param position
     */
    public void remove(int position) {
        if (position < 0 || position >= getItemCount()) {
            return;
        }
        mData.remove(position);
    }

    /**
     * 移除一项
     *
     * @param t
     */
    public void remove(@NonNull T t) {
        if (t != null) {
            mData.remove(t);
            notifyDataSetChanged();
        }
    }

    public BaseActivity getActivity() {
        return (BaseActivity) mContext;
    }

    public void clicks(View view, Action1<Void> action1) {
        if (getActivity() != null) {
            getActivity().rxClick(view, action1);
        }
    }

    public void longClicks(View view, Action1<Void> action1) {
        if (getActivity() != null) {
            getActivity().rxlongClick(view, action1);
        }
    }

    @CallSuper
    public void onDestory() {
        mContext = null;
        if (mData != null) {
            mData.clear();
        }
        mData = null;
    }
}
