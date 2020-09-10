package com.max.custom.loading;


import com.max.custom.loading.loading.Loading;
import com.max.custom.loading.style.ChasingDots;
import com.max.custom.loading.style.Circle;
import com.max.custom.loading.style.CubeGrid;
import com.max.custom.loading.style.DoubleBounce;
import com.max.custom.loading.style.FadingCircle;
import com.max.custom.loading.style.FoldingCube;
import com.max.custom.loading.style.MultiplePulse;
import com.max.custom.loading.style.MultiplePulseRing;
import com.max.custom.loading.style.Pulse;
import com.max.custom.loading.style.PulseRing;
import com.max.custom.loading.style.RotatingCircle;
import com.max.custom.loading.style.RotatingPlane;
import com.max.custom.loading.style.ThreeBounce;
import com.max.custom.loading.style.WanderingCubes;
import com.max.custom.loading.style.Wave;

/**
 * Created by ybq.
 */
public class LoadingFactory {

    public static Loading create(Style style) {
        Loading loading = null;
        switch (style) {
            case ROTATING_PLANE:
                loading = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                loading = new DoubleBounce();
                break;
            case WAVE:
                loading = new Wave();
                break;
            case WANDERING_CUBES:
                loading = new WanderingCubes();
                break;
            case PULSE:
                loading = new Pulse();
                break;
            case CHASING_DOTS:
                loading = new ChasingDots();
                break;
            case THREE_BOUNCE:
                loading = new ThreeBounce();
                break;
            case CIRCLE:
                loading = new Circle();
                break;
            case CUBE_GRID:
                loading = new CubeGrid();
                break;
            case FADING_CIRCLE:
                loading = new FadingCircle();
                break;
            case FOLDING_CUBE:
                loading = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                loading = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                loading = new MultiplePulse();
                break;
            case PULSE_RING:
                loading = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                loading = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return loading;
    }
}
