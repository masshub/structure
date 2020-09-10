package com.max.custom.loading.style;

import android.animation.ValueAnimator;
import android.os.Build;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.CircleLoading;
import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.loading.LoadingContainer;

/**
 * Created by ybq.
 */
public class DoubleBounce extends LoadingContainer {

    @Override
    public Loading[] onCreateChild() {
        return new Loading[]{
                new Bounce(), new Bounce()
        };
    }

    @Override
    public void onChildCreated(Loading... loadings) {
        super.onChildCreated(loadings);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            loadings[1].setAnimationDelay(1000);
        } else {
            loadings[1].setAnimationDelay(-1000);
        }
    }

    private class Bounce extends CircleLoading {

        Bounce() {
            setAlpha(153);
            setScale(0f);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new LoadingAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f).
                    duration(2000).
                    easeInOut(fractions)
                    .build();
        }
    }
}
