package com.easy.demo.ui.ndk;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.easy.apt.annotation.ActivityInject;
import com.easy.demo.R;
import com.easy.demo.databinding.EmptyBinding;
import com.easy.demo.databinding.TestNdkBinding;
import com.easy.demo.ui.empty.EmptyPresenter;
import com.easy.demo.ui.empty.EmptyView;
import com.easy.framework.base.BaseActivity;
import com.easy.ndk.NDKTools;

@ActivityInject
@Route(path = "/demo/TestNdkActivity", name = "NDK测试")
public class TestNdkActivity extends BaseActivity<EmptyPresenter, TestNdkBinding> implements EmptyView {

    @Override
    public int getLayoutId() {
        return R.layout.test_ndk;
    }

    @Override
    public void initView() {
        NDKTools.context = this;
    }

    public void getNativeData(View v) {
        viewBind.tvNdk.setText("native -v " + NDKTools.getNativeVersion());
    }

    public void getJavaData(View v) {
        viewBind.tvNdk.setText(NDKTools.getJavaVersion());
    }

    public void getJavaObjectMethod(View v) {
        viewBind.tvNdk.setText("native 计算3+5=" + NDKTools.addNative(3, 5));
    }

    public void getJavaStaticMethod(View v) {
        viewBind.tvNdk.setText(NDKTools.callStaticMethod());
    }

    public void handleExcept(View v) {
        try {
            NDKTools.handleExcept();
        } catch (Exception e) {
            e.printStackTrace();
            viewBind.tvNdk.setText(e.getMessage());
        }
    }

    public void getNativeDataBydy(View v) {
        viewBind.tvNdk.setText(NDKTools.stringFromJNIByDy());
    }

    public void getNativeAddBydy(View v) {
        viewBind.tvNdk.setText("native 计算2+7=" + NDKTools.addByDy(2, 7));
    }

    public void showAllFiles(View v) {
        NDKTools.showAllFiles(getFilesDir().getPath());
    }
}
