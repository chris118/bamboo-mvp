package com.hh.bamboobase.base;

import android.graphics.Bitmap;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by chrisw on 2016/3/19.
 */
public class BaseViewHolderHelper extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews = new SparseArray<>();
    private View mItemView;
    private Object mTag;
    private int mViewType;

    public BaseViewHolderHelper setViewType(int viewType){
        mViewType = viewType;
        return this;
    }

    public int getViewType(){
        return mViewType;
    }

    public BaseViewHolderHelper setTag(Object object){
        mTag = object;
        return this;
    }

    public Object getTag(){
        return mTag;
    }

    public BaseViewHolderHelper(View itemView) {
        super(itemView);
        mItemView = itemView;
    }

    public View getItemView(){
        return mItemView;
    }

    public <T extends View> T getView(@IdRes int resId){
        if(mViews.get(resId) != null){
            return (T) mViews.get(resId);
        }else{
            View view = mItemView.findViewById(resId);
            mViews.put(resId, view);
            return (T) view;
        }
    }

    public <T extends View> T setVisibility(@IdRes int resId, boolean visiblity){
        View view = getView(resId);
        view.setVisibility(visiblity ? View.VISIBLE : View.GONE);
        return (T) view;
    }


    public <T extends TextView> T setTextView(@IdRes int resId, @StringRes int strId){
       return setTextView(resId, strId, true);
    }

    public <T extends TextView> T setTextView(@IdRes int resId, @StringRes  int strId, boolean visibility){
        T view = getView(resId);
        view.setText(strId);
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
        return view;
    }

    public <T extends TextView> T setTextView(@IdRes int resId, @NonNull String str){
       return setTextView(resId, str, true);
    }

    public <T extends TextView> T setTextView(@IdRes int resId, @NonNull String str, boolean visibility){
        T view = getView(resId);
        view.setText(str);
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
        return view;
    }

    public <T extends TextView> T setTextViewLong(@IdRes int resId, long text){
      return setTextViewLong(resId, text, true);
    }

    public <T extends TextView> T setTextViewLong(@IdRes int resId, long text, boolean visiblity){
        T view = getView(resId);
        view.setText(String.valueOf(text));
        view.setVisibility(visiblity ? View.VISIBLE : View.GONE);
        return view;
    }


    public <T extends TextView> T setTextView(@IdRes int resId, @NonNull Spanned str){
        T view = getView(resId);
        view.setText(str);
        return view;
    }

    public <T extends TextView> T setHtmlText(@IdRes int resId, @NonNull String text){
        T view = getView(resId);
        view.setText(Html.fromHtml(text));
        return view;
    }

    public <T extends TextView> T setTextColor(@IdRes int resId, int color){
        T view = getView(resId);
        view.setTextColor(color);
        return view;
    }

    public View setBackgroundResource(@IdRes int resId, @DrawableRes int drawableId){
        View view = getView(resId);
        view.setBackgroundResource(drawableId);
        return view;
    }

    public View setBackgroundColor(@IdRes int resId, int color){
        View view = getView(resId);
        view.setBackgroundColor(color);
        return view;
    }

    public <T extends ImageView> T setImageResource(@IdRes int res, int drawableId){
        return setImageResource(res, drawableId, true);
    }

    public <T extends ImageView> T setImageResource(@IdRes int res, int drawableId, boolean visibility){
        T view = getView(res);
        view.setImageResource(drawableId);
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
        return view;
    }

    public <T extends ImageView> T setImageBitmap(@IdRes int res, @NonNull Bitmap bitmap){
        T view = getView(res);
        view.setImageBitmap(bitmap);
        return view;
    }

    public <T extends ProgressBar> T setMax(@IdRes int resId, int max){
        T view = getView(resId);
        view.setMax(max);
        return view;
    }

    public <T extends ProgressBar> T setProgress(@IdRes int resId, int progress){
        T view = getView(resId);
        view.setProgress(progress);
        return view;
    }

    public <T extends ProgressBar> T setSecondProgress(@IdRes int resId, int secondProgress){
        T view = getView(resId);
        view.setSecondaryProgress(secondProgress);
        return view;
    }

    public <T extends ProgressBar> T setMaxAndProgress(@IdRes int resId, int max, int progress){
        T view = getView(resId);
        view.setMax(max);
        view.setProgress(progress);
        return view;
    }

    public <T extends View> T setOnClickListener(@IdRes int resId, View.OnClickListener listener){
        return setOnClickListener(resId, null, listener);
    }

    public <T extends View> T setOnLongClickListener(@IdRes int resId, View.OnLongClickListener listener){
        T view = getView(resId);
        view.setOnLongClickListener(listener);
        return view;
    }

    public <T extends View> T setOnClickListener(@IdRes int resId, Object tag, View.OnClickListener listener){
        T view = getView(resId);
        view.setTag(tag);
        view.setOnClickListener(listener);
        return view;
    }

    public BaseViewHolderHelper setItemViewOnClick(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolderHelper setItemViewOnLongClick(View.OnLongClickListener listener){
        itemView.setOnLongClickListener(listener);
        return this;
    }

    public <T extends CompoundButton> T setCheck(@IdRes int resId, boolean isCheck){
        T view = getView(resId);
        view.setChecked(isCheck);
        return view;
    }
}
