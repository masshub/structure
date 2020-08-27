package com.max.custom.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.max.custom.R;
import com.max.custom.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Maker on 2020/8/25.
 * Description:
 */
public class CustomActivity extends AppCompatActivity {


    @BindView(R2.id.tv_custom)
    TextView tvCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.tv_custom)
    public void onViewClicked() {
        tvCustom.setText("CustomActivity");
    }
}
