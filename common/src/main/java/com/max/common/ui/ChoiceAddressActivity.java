package com.max.common.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.max.common.R;
import com.max.common.R2;
import com.max.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Maker on 2020/8/28.
 * Description:
 */
public class ChoiceAddressActivity extends AppCompatActivity {
    @BindView(R2.id.tv_back)
    TextView tvBack;
    @BindView(R2.id.tv_change_develop)
    TextView tvChangeDevelop;
    @BindView(R2.id.rl_develop)
    RelativeLayout rlDevelop;
    @BindView(R2.id.tv_change_test)
    TextView tvChangeTest;
    @BindView(R2.id.rl_test)
    RelativeLayout rlTest;
    @BindView(R2.id.tv_change_joint)
    TextView tvChangeJoint;
    @BindView(R2.id.rl_joint)
    RelativeLayout rlJoint;
    @BindView(R2.id.tv_production_pro)
    TextView tvProductionPro;
    @BindView(R2.id.rl_production_pro)
    RelativeLayout rlProductionPro;
    @BindView(R2.id.tv_production)
    TextView tvProduction;
    @BindView(R2.id.rl_production)
    RelativeLayout rlProduction;
    @BindView(R2.id.et_base_host)
    EditText etBaseHost;
    @BindView(R2.id.et_auth_host)
    EditText etAuthHost;
    @BindView(R2.id.tv_change_host)
    TextView tvChangeHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_address);
        ButterKnife.bind(this);

    }

    @OnClick({R2.id.tv_back, R2.id.tv_change_develop, R2.id.tv_change_test, R2.id.tv_change_joint,
            R2.id.tv_production_pro, R2.id.tv_production,R2.id.tv_change_host})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.tv_back:
                finish();
                break;
            case R2.id.tv_change_develop:
//                BaseApi.BASE_HOST = "https://api.t.zhilunkeji.com/";
//                BaseApi.AUTH_HOST = "https://auth.t.zhilunkeji.com/";
                ToastUtils.showShort("已切换至开发环境");
                finish();
                break;
            case R2.id.tv_change_test:
//                BaseApi.BASE_HOST = "http://api.zhilun-test.com/";
//                BaseApi.AUTH_HOST = "http://auth.zhilun-test.com/";
                ToastUtils.showShort( "已切换至测试环境");
                finish();
                break;
            case R2.id.tv_change_joint:
//                BaseApi.BASE_HOST = "http://api.zhilun-m.com/";
//                BaseApi.AUTH_HOST = "http://auth.zhilun-m.com/";
                ToastUtils.showShort( "已切换至联调环境");
                finish();
                break;
            case R2.id.tv_production_pro:
//                BaseApi.BASE_HOST = "https://api.t.zhilunkeji.com/";
//                BaseApi.AUTH_HOST = "https://auth.t.zhilunkeji.com/";
                ToastUtils.showShort( "已切换至准生产环境");
                finish();
                break;
            case R2.id.tv_production:
//                BaseApi.BASE_HOST = "https://api.zhilunkeji.com/";
//                BaseApi.AUTH_HOST = "https://auth.zhilunkeji.com/";
                ToastUtils.showShort("已切换至生产环境");
                finish();
                break;
            case R2.id.tv_change_host:
                String base_host = etBaseHost.getText().toString().trim();
                String auth_host = etAuthHost.getText().toString().trim();
                if (base_host == null) {
                    ToastUtils.showShort("BASE_HOST不能为空");
                    return;
                }
                if (auth_host == null) {
                    ToastUtils.showShort("AUTH_HOST不能为空");
                    return;
                }
//                BaseApi.BASE_HOST = base_host;
//                BaseApi.AUTH_HOST = auth_host;
                ToastUtils.showShort("已切换至您输入的环境");
                finish();
                break;
            default:
                break;
        }
    }





}
