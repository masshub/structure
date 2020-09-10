package com.max.custom.loading.animation.interpolator;

import android.view.animation.Interpolator;

import com.github.ybq.android.spinkit.animation.interpolator.PathInterpolatorCompat;

/**
 * Created by ybq.
 */
public class Ease {
    public static Interpolator inOut() {
        return PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f);
    }
}
