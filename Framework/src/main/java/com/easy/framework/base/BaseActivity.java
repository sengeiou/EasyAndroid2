package com.easy.framework.base;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;

import com.easy.apt.lib.InjectActivity;
import com.easy.framework.base.common.CommonActivity;
import com.easy.framework.manager.network.INetStateChange;
import com.easy.framework.manager.network.NetworkManager;
import com.easy.framework.manager.network.NetworkType;
import com.easy.framework.manager.screen.IScreenStateChange;
import com.easy.framework.manager.screen.ScreenManager;
import com.easy.framework.manager.screen.ScreenStateType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

/**
 * 添加通用的功能
 *
 * @param <P>
 * @param <V>
 */
public abstract class BaseActivity<P extends BasePresenter, V extends ViewDataBinding> extends CommonActivity implements BaseView, INetStateChange, IScreenStateChange {

    public V viewBind;
    @Inject
    public P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBind = DataBindingUtil.setContentView(this, getLayoutId());
        initStateBar();
        InjectActivity.inject(this);
        if (presenter != null) {
            presenter.attachView(context, this, this);
        }
        NetworkManager.registerObserver(this);
        ScreenManager.registerObserver(this);
        initView();
    }

    @Override
    public void onDestroy() {
        NetworkManager.unRegisterObserver(this);
        ScreenManager.unRegisterObserver(this);
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    @Override
    public void onNetDisconnected() {
        Log.d("onNetDisconnected", "无网络");
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        Log.d("onNetDisconnected", "有网络：" + networkType.name());
    }

    @Override
    public void onScreenState(ScreenStateType type) {
        Log.d("onScreenState", "屏幕状态：" + type.name());
    }

    public RxPermissions getRxPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        return rxPermissions;
    }

    public <T> AutoDisposeConverter<T> getAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this));
    }

    public <T> AutoDisposeConverter<T> getAutoDispose(Lifecycle.Event untilEvent) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, untilEvent));
    }

}
