package com.max.structure.ui.splash;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.max.common.base.BaseActivity;
import com.max.structure.R;
import com.max.structure.ui.login.LoginActivity;

import butterknife.BindView;

/**
 * Created by Maker on 2020/9/10.
 * Description:
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.lav_splash)
    LottieAnimationView lavSplash;

    @Override
    protected void initView() {
        lavSplash.setSpeed(2.0f);
        lavSplash.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                lavSplash.clearAnimation();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    @Override
    public int getContentView(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
