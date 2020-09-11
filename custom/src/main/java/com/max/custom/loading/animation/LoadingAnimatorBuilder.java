package com.max.custom.loading.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.util.Property;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.max.custom.loading.animation.interpolator.KeyFrameInterpolator;
import com.max.custom.loading.loading.Loading;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by ybq.
 */
public class LoadingAnimatorBuilder {

    private static final String TAG = "LoadingAnimatorBuilder";
    private Loading loading;
    private Interpolator interpolator;
    private int repeatCount = Animation.INFINITE;
    private long duration = 2000;
    private int startFrame = 0;
    private Map<String, FrameData> fds = new HashMap<>();


    class FrameData<T> {
        public FrameData(float[] fractions, Property property, T[] values) {
            this.fractions = fractions;
            this.property = property;
            this.values = values;
        }

        float[] fractions;
        Property property;
        T[] values;
    }

    class IntFrameData extends FrameData<Integer> {

        public IntFrameData(float[] fractions, Property property, Integer[] values) {
            super(fractions, property, values);
        }
    }

    class FloatFrameData extends FrameData<Float> {

        public FloatFrameData(float[] fractions, Property property, Float[] values) {
            super(fractions, property, values);
        }
    }

    public LoadingAnimatorBuilder(Loading loading) {
        this.loading = loading;
    }

    public LoadingAnimatorBuilder scale(float fractions[], Float... scale) {
        holder(fractions, Loading.SCALE, scale);
        return this;
    }

    public LoadingAnimatorBuilder alpha(float fractions[], Integer... alpha) {
        holder(fractions, Loading.ALPHA, alpha);
        return this;
    }

    @SuppressWarnings("unused")
    public LoadingAnimatorBuilder scaleX(float fractions[], Float... scaleX) {
        holder(fractions, Loading.SCALE, scaleX);
        return this;
    }

    public LoadingAnimatorBuilder scaleY(float fractions[], Float... scaleY) {
        holder(fractions, Loading.SCALE_Y, scaleY);
        return this;
    }

    public LoadingAnimatorBuilder rotateX(float fractions[], Integer... rotateX) {
        holder(fractions, Loading.ROTATE_X, rotateX);
        return this;
    }

    public LoadingAnimatorBuilder rotateY(float fractions[], Integer... rotateY) {
        holder(fractions, Loading.ROTATE_Y, rotateY);
        return this;
    }

    @SuppressWarnings("unused")
    public LoadingAnimatorBuilder translateX(float fractions[], Integer... translateX) {
        holder(fractions, Loading.TRANSLATE_X, translateX);
        return this;
    }


    @SuppressWarnings("unused")
    public LoadingAnimatorBuilder translateY(float fractions[], Integer... translateY) {
        holder(fractions, Loading.TRANSLATE_Y, translateY);
        return this;
    }


    public LoadingAnimatorBuilder rotate(float fractions[], Integer... rotate) {
        holder(fractions, Loading.ROTATE, rotate);
        return this;
    }

    public LoadingAnimatorBuilder translateXPercentage(float fractions[], Float... translateXPercentage) {
        holder(fractions, Loading.TRANSLATE_X_PERCENTAGE, translateXPercentage);
        return this;
    }

    public LoadingAnimatorBuilder translateYPercentage(float[] fractions, Float... translateYPercentage) {
        holder(fractions, Loading.TRANSLATE_Y_PERCENTAGE, translateYPercentage);
        return this;
    }

    private void holder(float[] fractions, Property property, Float[] values) {
        ensurePair(fractions.length, values.length);
        fds.put(property.getName(), new FloatFrameData(fractions, property, values));
    }


    private void holder(float[] fractions, Property property, Integer[] values) {
        ensurePair(fractions.length, values.length);
        fds.put(property.getName(), new IntFrameData(fractions, property, values));
    }

    private void ensurePair(int fractionsLength, int valuesLength) {
        if (fractionsLength != valuesLength) {
            throw new IllegalStateException(String.format(
                    Locale.getDefault(),
                    "The fractions.length must equal values.length, " +
                            "fraction.length[%d], values.length[%d]",
                    fractionsLength,
                    valuesLength));
        }
    }


    public LoadingAnimatorBuilder interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public LoadingAnimatorBuilder easeInOut(float... fractions) {
        interpolator(KeyFrameInterpolator.easeInOut(
                fractions
        ));
        return this;
    }


    public LoadingAnimatorBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    @SuppressWarnings("unused")
    public LoadingAnimatorBuilder repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public LoadingAnimatorBuilder startFrame(int startFrame) {
        if (startFrame < 0) {
            Log.w(TAG, "startFrame should always be non-negative");
            startFrame = 0;
        }
        this.startFrame = startFrame;
        return this;
    }

    public ObjectAnimator build() {

        PropertyValuesHolder[] holders = new PropertyValuesHolder[fds.size()];
        int i = 0;
        for (Map.Entry<String, FrameData> fd : fds.entrySet()) {
            FrameData data = fd.getValue();
            Keyframe[] keyframes = new Keyframe[data.fractions.length];
            float[] fractions = data.fractions;
            float startF = fractions[startFrame];
            for (int j = startFrame; j < (startFrame + data.values.length); j++) {
                int key = j - startFrame;
                int vk = j % data.values.length;
                float fraction = fractions[vk] - startF;
                if (fraction < 0) {
                    fraction = fractions[fractions.length - 1] + fraction;
                }
                if (data instanceof IntFrameData) {
                    keyframes[key] = Keyframe.ofInt(fraction, (Integer) data.values[vk]);
                } else if (data instanceof FloatFrameData) {
                    keyframes[key] = Keyframe.ofFloat(fraction, (Float) data.values[vk]);
                } else {
                    keyframes[key] = Keyframe.ofObject(fraction, data.values[vk]);
                }
            }
            holders[i] = PropertyValuesHolder.ofKeyframe(data.property, keyframes);
            i++;
        }

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(loading,
                holders);
        animator.setDuration(duration);
        animator.setRepeatCount(repeatCount);
        animator.setInterpolator(interpolator);
        return animator;
    }

}
