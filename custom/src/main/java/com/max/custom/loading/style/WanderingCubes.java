package com.max.custom.loading.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.loading.LoadingContainer;
import com.max.custom.loading.loading.RectLoading;

/**
 * Created by ybq.
 */
public class WanderingCubes extends LoadingContainer {

    @Override
    public Loading[] onCreateChild() {
        return new Loading[]{
                new Cube(0),
                new Cube(3)
        };
    }

    @Override
    public void onChildCreated(Loading... sprites) {
        super.onChildCreated(sprites);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sprites[1].setAnimationDelay(-900);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        bounds = clipSquare(bounds);
        super.onBoundsChange(bounds);
        for (int i = 0; i < getChildCount(); i++) {
            Loading loading = getChildAt(i);
            loading.setDrawBounds(
                    bounds.left,
                    bounds.top,
                    bounds.left + bounds.width() / 4,
                    bounds.top + bounds.height() / 4
            );
        }
    }

    private class Cube extends RectLoading {
        int startFrame;

        public Cube(int startFrame) {
            this.startFrame = startFrame;
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.25f, 0.5f, 0.51f, 0.75f, 1f};
            LoadingAnimatorBuilder builder = new LoadingAnimatorBuilder(this).
                    rotate(fractions, 0, -90, -179, -180, -270, -360).
                    translateXPercentage(fractions, 0f, 0.75f, 0.75f, 0.75f, 0f, 0f).
                    translateYPercentage(fractions, 0f, 0f, 0.75f, 0.75f, 0.75f, 0f).
                    scale(fractions, 1f, 0.5f, 1f, 1f, 0.5f, 1f).
                    duration(1800).
                    easeInOut(fractions);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                builder.
                        startFrame(startFrame);
            }
            return builder.build();
        }
    }
}
