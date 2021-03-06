package com.easy.app.ui.guide;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.easy.app.R;
import com.easy.app.base.AppSharePreferences;
import com.easy.app.databinding.GuideBinding;
import com.easy.apt.annotation.ActivityInject;
import com.easy.apt.lib.SharePreference;
import com.easy.framework.base.BaseActivity;

@ActivityInject
@Route(path = "/app/GuideActivity", name = "引导页")
public class GuideActivity extends BaseActivity<GuidePresenter, GuideBinding> implements GuideView {

    @Override
    public int getLayoutId() {
        return R.layout.guide;
    }

    @Override
    public void initView() {
        closeSwipeBackLayout();
        SharePreference.get(this, AppSharePreferences.class).setGoGuide(false);
    }

    public void goActivity(View view) {
        ARouter.getInstance().build("/app/MainActivity").navigation();
    }
}
