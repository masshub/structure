package com.max.custom.loading.loading;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.max.custom.loading.animation.AnimationUtils;


/**
 * Created by ybq.
 */
public abstract class LoadingContainer extends Loading {

    private Loading[] loadings;

    private int color;

    public LoadingContainer() {
        loadings = onCreateChild();
        initCallBack();
        onChildCreated(loadings);
    }

    private void initCallBack() {
        if (loadings != null) {
            for (Loading loading: loadings) {
                loading.setCallback(this);
            }
        }
    }

    public void onChildCreated(Loading... loadings) {

    }

    public int getChildCount() {
        return loadings == null ? 0 : loadings.length;
    }

    public Loading getChildAt(int index) {
        return loadings == null ? null : loadings[index];
    }

    @Override
    public void setColor(int color) {
        this.color = color;
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setColor(color);
        }
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawChild(canvas);
    }

    public void drawChild(Canvas canvas) {
        if (loadings != null) {
            for (Loading loading : loadings) {
                int count = canvas.save();
                loading.draw(canvas);
                canvas.restoreToCount(count);
            }
        }
    }

    @Override
    protected void drawSelf(Canvas canvas) {
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        for (Loading loading : loadings) {
            loading.setBounds(bounds);
        }
    }

    @Override
    public void start() {
        super.start();
        AnimationUtils.start(loadings);
    }

    @Override
    public void stop() {
        super.stop();
        AnimationUtils.stop(loadings);
    }

    @Override
    public boolean isRunning() {
        return AnimationUtils.isRunning(loadings) || super.isRunning();
    }

    public abstract Loading[] onCreateChild();

    @Override
    public ValueAnimator onCreateAnimation() {
        return null;
    }
}
