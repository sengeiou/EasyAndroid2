package com.easy.demo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.easy.apt.annotation.FragmentInject;
import com.easy.demo.R;
import com.easy.demo.databinding.TestFragmentBinding;
import com.easy.framework.base.BaseFragment;
import com.easy.net.event.FragmentEvent;

@FragmentInject
public class TestFragment extends BaseFragment<TestFragmentPresenter, TestFragmentBinding> implements TestFragmentView<FragmentEvent> {

    @Override
    public int getLayoutId() {
        return R.layout.test_fragment;
    }

    @Override
    public void initView(View view) {
        viewBind.tvName.setText(tag + "====>");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getString("type");
        }
    }
}
