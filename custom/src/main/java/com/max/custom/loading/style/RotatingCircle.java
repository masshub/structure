package com.max.custom.loading.style;

import android.animation.ValueAnimator;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.CircleLoading;

public class RotatingCircle extends CircleLoading {

    @Override
    public ValueAnimator onCreateAnimation() {
        float fractions[] = new float[]{0f, 0.5f, 1f};
        return new LoadingAnimatorBuilder(this).
                rotateX(fractions, 0, -180, -180).
                rotateY(fractions, 0, 0, -180).
                duration(1200).
                easeInOut(fractions)
                .build();
    }
}
