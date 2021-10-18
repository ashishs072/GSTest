package com.example.gstest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gstest.R;
import com.example.gstest.db.AppDataBase;
import com.example.gstest.db.DataBaseBuilder;
import com.example.gstest.db.entity.PictureInfo;
import com.example.gstest.model.CurrentDateResponse;
import com.example.gstest.ui.PictureDetailActivity;
import com.example.gstest.util.AppExecutor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {
    private Context context;
    private List<CurrentDateResponse> list;
    private String offline;

    public PictureListAdapter(Context context, List<CurrentDateResponse> list,String offline) {
        this.context = context;
        this.list = list;
        this.offline = offline;
    }

    @NonNull
    @NotNull
    @Override
    public PictureListAdapter.PictureViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_picture_card_view, parent, false);

        return new PictureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PictureListAdapter.PictureViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getImageUrl())
                .override(1600, 1600)
                .dontAnimate()
                .into(holder.imageUrl);
        holder.pictureTitle.setText(list.get(position).getTitle());
        holder.pictureFavourite.setBackgroundResource(R.drawable.ic_baseline_star_border_24);
        if(list.get(position).getFavouriteFlag()) {
            holder.pictureFavourite.setBackgroundResource(R.drawable.ic_baseline_star_24);
        } else {
            holder.pictureFavourite.setBackgroundResource(R.drawable.ic_baseline_star_border_24);
        }
        if(offline.equals("offline")){
            holder.pictureDownload.setVisibility(View.GONE);
        }else {
            holder.pictureDownload.setVisibility(View.VISIBLE);
        }
        holder.pictureFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getFavouriteFlag()) {
                    holder.pictureFavourite.setBackgroundResource(R.drawable.ic_baseline_star_24);
                } else {
                    holder.pictureFavourite.setBackgroundResource(R.drawable.ic_baseline_star_border_24);
                }
                /*if(list.get(position).getFavouriteFlag()) {
                    PictureInfo info = new PictureInfo();
                    info.setDate(list.get(position).getDate());
                    info.setExplanation(list.get(position).getExplanation());
                    info.setTitle(list.get(position).getTitle());
                    info.setUrl(list.get(position).getImageUrl());
                    info.setFlag(true);
                    AppExecutor.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            DataBaseBuilder.getInstance(context).AppDataBase().pictureInfoDao().insertPictureInfo(info);
                        }
                    });

                }*/
                list.get(position).setFavouriteFlag(!list.get(position).getFavouriteFlag());
            }
        });
        holder.imageUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(context, PictureDetailActivity.class);
             intent.putExtra("CurrentDateData",list.get(position));
             context.startActivity(intent);
            }
        });
        holder.pictureDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureInfo info = new PictureInfo();
                info.setDate(list.get(position).getDate());
                info.setExplanation(list.get(position).getExplanation());
                info.setTitle(list.get(position).getTitle());
                info.setUrl(list.get(position).getImageUrl());
                info.setFlag(true);
                AppExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        DataBaseBuilder.getInstance(context).AppDataBase().pictureInfoDao().insertPictureInfo(info);
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private TextView pictureTitle;
        private ImageView pictureFavourite;
        private ImageView imageUrl;
        private ImageView pictureDownload;
        public PictureViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            pictureFavourite = itemView.findViewById(R.id.pictureFavourite);
            pictureTitle = itemView.findViewById(R.id.pictureTitle);
            imageUrl = itemView.findViewById(R.id.dayPicture);
            pictureDownload = itemView.findViewById(R.id.pictureDownload);

        }
    }
}
