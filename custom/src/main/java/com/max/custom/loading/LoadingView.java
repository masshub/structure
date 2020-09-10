package com.max.custom.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.max.custom.R;
import com.max.custom.loading.loading.Loading;

/**
 * Created by ybq.
 */
public class LoadingView extends ProgressBar {

    private Style mStyle;
    private int mColor;
    private Loading mLoading;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.LoadingViewStyle);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.LoadingView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingView, defStyleAttr,
                defStyleRes);
        mStyle = Style.values()[a.getInt(R.styleable.LoadingView_Loading_Style, 0)];
        mColor = a.getColor(R.styleable.LoadingView_Loading_Color, Color.WHITE);
        a.recycle();
        init();
        setIndeterminate(true);
    }

    private void init() {
        Loading loading = LoadingFactory.create(mStyle);
        loading.setColor(mColor);
        setIndeterminateDrawable(loading);
    }

    @Override
    public void setIndeterminateDrawable(Drawable d) {
        if (!(d instanceof Loading)) {
            throw new IllegalArgumentException("this d must be instanceof Sprite");
        }
        setIndeterminateDrawable((Loading) d);
    }

    public void setIndeterminateDrawable(Loading d) {
        super.setIndeterminateDrawable(d);
        mLoading = d;
        if (mLoading.getColor() == 0) {

            mLoading.setColor(mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == VISIBLE) {

            mLoading.start();
        }
    }

    @Override
    public Loading getIndeterminateDrawable() {
        return mLoading;
    }

    public void setColor(int color) {
        this.mColor = color;
        if (mLoading != null) {

            mLoading.setColor(color);
        }
        invalidate();
    }

    @Override
    public void unscheduleDrawable(Drawable who) {
        super.unscheduleDrawable(who);
        if (who instanceof Loading) {

            ((Loading) who).stop();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            if (mLoading != null && getVisibility() == VISIBLE) {

                mLoading.start();
            }
        }
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        if (screenState == View.SCREEN_STATE_OFF) {

            if (mLoading != null) {

                mLoading.stop();
            }
        }
    }
}
