package com.max.custom.loading.style;

import android.animation.ValueAnimator;
import android.os.Build;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.CircleLayoutContainer;
import com.max.custom.loading.loading.CircleLoading;
import com.max.custom.loading.loading.Loading;


/**
 * Created by ybq.
 */
public class FadingCircle extends CircleLayoutContainer {

    @Override
    public Loading[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setAnimationDelay(1200 / 12 * i);
            } else {
                dots[i].setAnimationDelay(1200 / 12 * i + -1200);
            }
        }
        return dots;
    }

    private class Dot extends CircleLoading {

        Dot() {
            setAlpha(0);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.39f, 0.4f, 1f};
            return new LoadingAnimatorBuilder(this).
                    alpha(fractions, 0, 0, 255, 0).
                    duration(1200).
                    easeInOut(fractions).build();
        }
    }
}
