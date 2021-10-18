package com.example.gstest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gstest.R;
import com.example.gstest.model.CurrentDateResponse;

public class PictureDetailActivity extends Activity {
    private Intent intent;
    private String pictureName;
    private String pictureDate;
    private String pictureDetail;
    private String pictureUrl;
    private ImageView ivPictureImage;
    private TextView tvPictureTitle;
    private TextView tvPictureDate;
    private TextView tvPictureDetail;
    private CurrentDateResponse currentData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_item_list);
        intent = getIntent();
        ivPictureImage = findViewById(R.id.dayPicture);
        tvPictureTitle = findViewById(R.id.pictureTitle);
        tvPictureDate = findViewById(R.id.pictureDate);
        tvPictureDetail = findViewById(R.id.pictureDescription);
        currentData = (CurrentDateResponse) intent.getSerializableExtra("CurrentDateData");
        pictureUrl = currentData.getImageUrl();
        pictureDate = currentData.getDate();
        pictureDetail = currentData.getExplanation();
        pictureName = currentData.getTitle();
        Glide.with(this)
                .load(pictureUrl)
                .override(1600, 1600)
                .dontAnimate()
                .into(ivPictureImage);
        tvPictureDate.setText(pictureDate);
        tvPictureDetail.setText(pictureDetail);
        tvPictureTitle.setText(pictureName);
    }
}
