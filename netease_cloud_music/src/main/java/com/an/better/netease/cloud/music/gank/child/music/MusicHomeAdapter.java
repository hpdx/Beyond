package com.an.better.netease.cloud.music.gank.child.music;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.FirstPublishBlockBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.HomeBannerViewHolder;
import com.an.better.netease.cloud.music.gank.child.music.model.FocusBean;
import com.an.better.netease.cloud.music.gank.child.music.model.Mix1Bean;
import com.an.better.netease.cloud.music.gank.child.music.model.MovieSongs;
import com.an.better.netease.cloud.music.gank.child.music.model.SongMenuBean;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.FirstPublishViewHolder;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.MovieSongsViewHolder;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.SongMenuViewHolder;
import com.anbetter.beyond.adapter.BaseListAdapter;

import java.util.List;

/**
 * Created by android_ls on 2018/2/11.
 */

public class MusicHomeAdapter extends BaseListAdapter<MusicListRequest> {

    private EverydayEventHandler mEventHandler;
    public MusicHomeAdapter(MusicListRequest baseList, EverydayEventHandler eventHandler) {
        super(baseList);
        this.mEventHandler = eventHandler;
    }

    @Override
    public int getBLMItemViewType(int position) {
        return mList.getItems().get(position).first;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MusicListRequest.VIEW_TYPE_BANNER:
                return new HomeBannerViewHolder(mLayoutInflater, parent, mEventHandler);
            case MusicListRequest.VIEW_TYPE_FIRST_PUBLISH:
                return new FirstPublishViewHolder(FirstPublishBlockBinding.inflate(mLayoutInflater, parent, false), mEventHandler);
            case MusicListRequest.VIEW_TYPE_MOVIE_SONGS:
                return new MovieSongsViewHolder(FirstPublishBlockBinding.inflate(mLayoutInflater, parent, false), mEventHandler);
            case MusicListRequest.VIEW_TYPE_SONG_MENU:
                return new SongMenuViewHolder(FirstPublishBlockBinding.inflate(mLayoutInflater, parent, false), mEventHandler);
            default:
                String unknown = "Unknown type for onCreateViewHolder" + viewType;
                throw new IllegalStateException(unknown);
        }
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        switch (viewType) {
            case MusicListRequest.VIEW_TYPE_BANNER:
                ((HomeBannerViewHolder) holder).bind((FocusBean) mList.getItems().get(position).second, position);
                break;
            case MusicListRequest.VIEW_TYPE_FIRST_PUBLISH:
                ((FirstPublishViewHolder) holder).bind((Mix1Bean) mList.getItems().get(position).second, position);
                break;
            case MusicListRequest.VIEW_TYPE_MOVIE_SONGS:
                ((MovieSongsViewHolder) holder).bind((List<MovieSongs>) mList.getItems().get(position).second, position);
                break;
            case MusicListRequest.VIEW_TYPE_SONG_MENU:
                ((SongMenuViewHolder) holder).bind((List<SongMenuBean>) mList.getItems().get(position).second, position);
                break;
        }
    }

}
