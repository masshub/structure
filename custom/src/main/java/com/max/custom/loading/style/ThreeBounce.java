package com.max.custom.loading.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.CircleLoading;
import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.loading.LoadingContainer;

/**
 * Created by ybq.
 */
public class ThreeBounce extends LoadingContainer {

    @Override
    public Loading[] onCreateChild() {
        return new Loading[]{
                new Bounce(),
                new Bounce(),
                new Bounce()
        };
    }

    @Override
    public void onChildCreated(Loading... loadings) {
        super.onChildCreated(loadings);
        loadings[1].setAnimationDelay(160);
        loadings[2].setAnimationDelay(320);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int radius = bounds.width() / 8;
        int top = bounds.centerY() - radius;
        int bottom = bounds.centerY() + radius;

        for (int i = 0; i < getChildCount(); i++) {
            int left = bounds.width() * i / 3
                    + bounds.left;
            getChildAt(i).setDrawBounds(
                    left, top, left + radius * 2, bottom
            );
        }
    }

    private class Bounce extends CircleLoading {

        Bounce() {
            setScale(0f);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.4f, 0.8f, 1f};
            return new LoadingAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f, 0f).
                    duration(1400).
                    easeInOut(fractions)
                    .build();
        }
    }
}
