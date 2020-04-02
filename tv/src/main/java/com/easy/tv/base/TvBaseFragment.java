package com.easy.tv.base;

import android.util.TypedValue;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.easy.framework.base.BaseFragment;
import com.easy.framework.base.BasePresenter;
import com.easy.tv.R;
import com.owen.focus.FocusBorder;

public abstract class TvBaseFragment<P extends BasePresenter, V extends ViewDataBinding> extends BaseFragment<P, V> {
    public FocusBorder mFocusBorder;

    public void onMoveFocusBorder(View focusedView, float scale) {
        if (null != mFocusBorder) {
            mFocusBorder.onFocus(focusedView, FocusBorder.OptionsFactory.get(scale, scale));
        }
    }

    @Override
    public void initView(View view) {
        mFocusBorder = new FocusBorder.Builder()
                .asColor()
                .borderColorRes(R.color.actionbar_color)
                .borderWidth(TypedValue.COMPLEX_UNIT_DIP, 3f)
                .shadowColorRes(R.color.green_bright)
                .shadowWidth(TypedValue.COMPLEX_UNIT_DIP, 5f)
                .build(this);
    }
}
