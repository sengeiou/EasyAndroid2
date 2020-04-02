package com.easy.tv.ui.album;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easy.loadimage.ImageConfig;
import com.easy.tv.R;
import com.easy.tv.bean.Album;

import java.util.ArrayList;

public class AlbumAdapter extends BaseQuickAdapter<Album, BaseViewHolder> {

    Context context;
    public AlbumAdapter(Context context) {
        super(R.layout.album_item, new ArrayList<>());
        this.context = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Album album) {

        ImageView ivIcon = helper.getView(R.id.ivIcon);
        ImageConfig.create(context)
                .url(album.getUrl())
                .errorPic(R.drawable.error_icon)
                .placeholder(R.drawable.error_icon)
                .imageView(ivIcon).end();
        helper.setText(R.id.tvTitle, album.getName());
    }
}
