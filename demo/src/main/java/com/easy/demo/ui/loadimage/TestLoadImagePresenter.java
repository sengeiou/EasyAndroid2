package com.easy.demo.ui.loadimage;

import android.Manifest;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.easy.demo.R;
import com.easy.framework.base.BasePresenter;
import com.easy.framework.base.DataObservable;
import com.easy.framework.base.DataObserver;
import com.easy.loadimage.EasyLoadImage;
import com.easy.net.event.ActivityEvent;
import com.easy.utils.FileUtils;
import com.easy.utils.ToastUtils;
import com.easy.utils.Utils;
import com.easy.utils.base.FileConstant;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TestLoadImagePresenter extends BasePresenter<TestLoadImageView> {
    @Inject
    public TestLoadImagePresenter() {

    }

    /**
     * 请求权限
     *
     * @param permissions
     */
    public void requestPermission(RxPermissions permissions) {
        DataObservable.builder(permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE))
                .lifecycleProvider(mvpView.getRxLifecycle())
                .activityEvent(ActivityEvent.DESTROY)
                .dataObserver(new DataObserver<Boolean>() {
                    @Override
                    protected void onSuccess(Boolean granted) {
                        mvpView.permissionCallback(granted, null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.permissionCallback(null, e);
                    }
                });
    }

    public void downloadImage(String imageUrl) {
        String fileExtension = FileUtils.getFileExtension(imageUrl, "png");
        String fileName = Utils.buildString("img_", System.currentTimeMillis(), ".", fileExtension);
        String saveFile = FileUtils.getFilePath(FileConstant.TYPE_PHOTO, getContext()) + fileName;
        Observable<File> observable = EasyLoadImage.downloadImageToGallery(getContext(), imageUrl, saveFile);
        DataObservable.builder(observable)
                .lifecycleProvider(mvpView.getRxLifecycle())
                .activityEvent(ActivityEvent.DESTROY)
                .dataObserver(new DataObserver<File>() {
                    @Override
                    protected void onSuccess(File file) {
                        mvpView.downloadCallback(file, null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.downloadCallback(null, e);
                    }
                });
    }
}