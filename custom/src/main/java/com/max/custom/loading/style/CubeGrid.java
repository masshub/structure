package com.max.custom.loading.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.max.custom.loading.animation.LoadingAnimatorBuilder;
import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.loading.LoadingContainer;
import com.max.custom.loading.loading.RectLoading;

/**
 * Created by ybq.
 */
public class CubeGrid extends LoadingContainer {

    @Override
    public Loading[] onCreateChild() {
        int delays[] = new int[]{
                200, 300, 400
                , 100, 200, 300
                , 0, 100, 200
        };
        GridItem[] gridItems = new GridItem[9];
        for (int i = 0; i < gridItems.length; i++) {
            gridItems[i] = new GridItem();
            gridItems[i].setAnimationDelay(delays[i]);
        }
        return gridItems;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int width = (int) (bounds.width() * 0.33f);
        int height = (int) (bounds.height() * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int x = i % 3;
            int y = i / 3;
            int l = bounds.left + x * width;
            int t = bounds.top + y * height;
            Loading loading = getChildAt(i);
            loading.setDrawBounds(l, t, l + width, t + height);
        }
    }

    private class GridItem extends RectLoading {
        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.35f, 0.7f, 1f};
            return new LoadingAnimatorBuilder(this).
                    scale(fractions, 1f, 0f, 1f, 1f).
                    duration(1300).
                    easeInOut(fractions)
                    .build();
        }
    }
}
