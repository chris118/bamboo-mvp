package com.hh.bamboobase.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hh.bamboobase.R;
import com.hh.bamboobase.base.BaseActivity;
import com.hh.bamboobase.utils.DensityUtils;


import rx.functions.Action1;

/**
 * 标题栏
 * Created by chrisw on 2015/11/3.
 */
public class NavigationBar extends RelativeLayout {

    private TextView mTvLeftTitle;
    private TextView mTvRightTitle;
    private TextView mTvMainTitle;
    private View mLeftView;
    private View mRightView;

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initNavigationBar(context, attrs);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(Context context) {
        this(context, null);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initNavigationBar(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar);
        String mainTitle = array.getString(R.styleable.NavigationBar_nav_main_title);
        String leftTitle = array.getString(R.styleable.NavigationBar_nav_left_title);
        String rightTitle = array.getString(R.styleable.NavigationBar_nav_right_title);
        int mainTitleColor = array.getColor(R.styleable.NavigationBar_nav_main_title_color, -1);
        int leftTitleColor = array.getColor(R.styleable.NavigationBar_nav_left_title_color, -1);
        int rightTitleColor = array.getColor(R.styleable.NavigationBar_nav_right_title_color, -1);
        int mainTitleSize = array.getDimensionPixelSize(R.styleable.NavigationBar_nav_main_title_size, -1);
        int leftTitleSize = array.getDimensionPixelSize(R.styleable.NavigationBar_nav_left_title_size, -1);
        int rightTitleSize = array.getDimensionPixelSize(R.styleable.NavigationBar_nav_right_title_size, -1);
        Drawable leftDrawable = array.getDrawable(R.styleable.NavigationBar_nav_left_icon);
        Drawable rightDrawable = array.getDrawable(R.styleable.NavigationBar_nav_right_icon);
        array.recycle();
        View v = LayoutInflater.from(getContext()).inflate(R.layout.widget_navigation_bar, this);
        mTvLeftTitle = (TextView) v.findViewById(R.id.left_title);
        mTvRightTitle = (TextView) v.findViewById(R.id.right_title);
        mTvMainTitle = (TextView) v.findViewById(R.id.main_title);
        mLeftView = v.findViewById(R.id.leftView);
        mRightView = v.findViewById(R.id.rightView);
        mTvLeftTitle.setVisibility(INVISIBLE);
        mTvRightTitle.setVisibility(INVISIBLE);
        mLeftView.setVisibility(VISIBLE);
        mRightView.setVisibility(VISIBLE);
        setMainTitleColor(Color.WHITE);
        setLeftTitleColor(Color.WHITE);
        setRightTitleColor(Color.WHITE);
        if(!TextUtils.isEmpty(mainTitle)){
            setMainTitle(mainTitle);
        }
        if(!TextUtils.isEmpty(leftTitle)){
            setLeftTitle(leftTitle);
        }
        if(!TextUtils.isEmpty(rightTitle)){
            setRightTitle(rightTitle);
        }
        if(mainTitleColor != -1){
            setMainTitleColor(mainTitleColor);
        }
        if(leftTitleColor != -1){
            setLeftTitleColor(leftTitleColor);
        }
        if(rightTitleColor != -1){
            setRightTitleColor(rightTitleColor);
        }
        if(mainTitleSize != -1){
            setMainTitleSize(mainTitleSize);
        }
        if(leftTitleSize != -1){
            setLeftTitleSize(leftTitleSize);
        }
        if(rightTitleSize != -1){
            setRightTitleSize(rightTitleSize);
        }
        if(leftDrawable != null){
            setLeftIcon(leftDrawable);
        }
        if(rightDrawable != null){
            setRightIcon(rightDrawable);
        }
    }

    public void setMainTitle(String title) {
       mTvMainTitle.setText(title);
    }

    public void setMainTitleColor(int colorId) {
        mTvMainTitle.setTextColor(colorId);
    }

    public void setMainTitleSize(int size) {
        mTvMainTitle.setTextSize(size);
    }

    public void setLeftTitle(String title) {
        mTvLeftTitle.setText(title);
        mTvLeftTitle.setVisibility(VISIBLE);
    }

    public void setLeftIcon(Drawable drawable) {
        mTvLeftTitle.setVisibility(VISIBLE);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvLeftTitle.setCompoundDrawables(drawable, null, null, null);
        //设置TextView的drawableRight //设置图片和text之间的间距
        mTvLeftTitle.setCompoundDrawablePadding(4);
    }

    public void setLeftTitleColor(int colorId) {
        mTvLeftTitle.setTextColor(colorId);
    }

    public void setLeftTitleSize(int size) {
        mTvLeftTitle.setTextSize(size);
    }

    public void setRightTitle(String title) {
        mTvRightTitle.setText(title);
        mTvRightTitle.setVisibility(VISIBLE);
    }

    public void setRightIcon(Drawable drawable) {
        mTvRightTitle.setVisibility(VISIBLE);

        int right = DensityUtils.dp2px(getContext(), 16);
        int bottom = DensityUtils.dp2px(getContext(), 16);
        drawable.setBounds(0, 0, right, bottom);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvRightTitle.setCompoundDrawables(null, null, drawable, null);

        //设置TextView的drawableRight //设置图片和text之间的间距
        mTvRightTitle.setCompoundDrawablePadding(4);
    }

    public void setRightTitleColor(int colorId) {
        mTvRightTitle.setTextColor(colorId);
    }

    public void setRightTitleSize(int size) {
        mTvRightTitle.setTextSize(size);
    }

    public void setRightVisibility(boolean visibility) {
        mRightView.setVisibility(visibility ? VISIBLE : INVISIBLE);
    }
    /**
     * getLeftView
     */
    public View getLeftView() {
        return mLeftView;
    }

    /**
     * getRightView
     */
    public View getRightView() {
        return mRightView;
    }

    /**
     * getMainView
     */
    public View getMainView() {
        return mTvMainTitle;
    }

    public TextView setRightTextViewEnable(boolean enable){
        mRightView.setEnabled(enable);
        if(enable) {
            setRightTitleColor(Color.WHITE);
        }else{
            mTvRightTitle.setTextColor(Color.parseColor("#959595"));
        }
        return mTvRightTitle;
    }


    public void setLeftClick(BaseActivity activity, Action1 action1){
        activity.rxClick(mLeftView, action1);
    }

    public void setRightClick(BaseActivity activity, Action1 action1){
        activity.rxClick(mRightView, action1);
    }

}
