package com.max.custom.loading.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.max.custom.loading.loading.Loading;

/**
 * Created by ybq.
 */
public class AnimationUtils {

    public static void start(Animator animator) {
        if (animator != null && !animator.isStarted()) {
            animator.start();
        }
    }

    public static void stop(Animator animator) {
        if (animator != null && !animator.isRunning()) {
            animator.end();
        }
    }

    public static void start(Loading... loadings) {
        for (Loading loading : loadings) {
            loading.start();
        }
    }

    public static void stop(Loading... loadings) {

        for (Loading loading : loadings) {
            loading.stop();
        }
    }

    public static boolean isRunning(Loading... loadings) {
        for (Loading loading : loadings) {
            if (loading.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRunning(ValueAnimator animator) {
        return animator != null && animator.isRunning();
    }

    public static boolean isStarted(ValueAnimator animator) {
        return animator != null && animator.isStarted();
    }
}
