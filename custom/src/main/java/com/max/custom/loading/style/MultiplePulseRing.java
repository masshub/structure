package com.max.custom.loading.style;

import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.loading.LoadingContainer;

/**
 * Created by ybq.
 */
public class MultiplePulseRing extends LoadingContainer {

    @Override
    public Loading[] onCreateChild() {
        return new Loading[]{
                new PulseRing(),
                new PulseRing(),
                new PulseRing(),
        };
    }

    @Override
    public void onChildCreated(Loading... loadings) {
        for (int i = 0; i < loadings.length; i++) {
            loadings[i].setAnimationDelay(200 * (i + 1));
        }
    }
}
