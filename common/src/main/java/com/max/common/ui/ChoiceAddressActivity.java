package com.max.common.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.max.common.R;
import com.max.common.R2;
import com.max.common.ui.custom.circle_menu.CircleMenuView;
import com.max.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class ChoiceAddressActivity extends AppCompatActivity {
    private CircleMenuView mCircleMenuView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_address);

        mCircleMenuView = findViewById(R.id.cmv_menu);

        mCircleMenuView.setEventListener(new CircleMenuView.EventListener(){
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }

        });


    }






}
