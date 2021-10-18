package com.example.gstest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gstest.R;
import com.example.gstest.adapter.PictureListAdapter;
import com.example.gstest.db.DataBaseBuilder;
import com.example.gstest.db.entity.PictureInfo;
import com.example.gstest.model.CurrentDateResponse;
import com.example.gstest.network.ApiClient;
import com.example.gstest.network.ApiInterface;
import com.example.gstest.util.AppExecutor;
import com.example.gstest.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<CurrentDateResponse> listPicData;
    private ApiInterface apiInterface;
    private RecyclerView pictureList;

    Intent intent;
    private FrameLayout progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pictureList = findViewById(R.id.picture_list);
        listPicData = new ArrayList<>();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        intent = getIntent();
        progressbar = findViewById(R.id.picture_progress_bar);
        String specificDate = intent.getStringExtra("SpecificDate");
        String dateFrom = intent.getStringExtra("DateFrom");
        String dateTo = intent.getStringExtra("DateTo");
        if(specificDate != null && !specificDate.equals("")){
            progressbar.setVisibility(View.VISIBLE);
            progressbar.bringToFront();
            getServiceCallSpecificDate(specificDate);
        } else {
            progressbar.setVisibility(View.VISIBLE);
            progressbar.bringToFront();
            getServiceCallDateRange(dateFrom,dateTo);
        }

        //getServiceCall();
        //setRecyclerViewData("online");


    }

    private void setRecyclerViewData(String offlineData) {
        PictureListAdapter adapter = new PictureListAdapter(this, listPicData, offlineData);
        pictureList.setLayoutManager(new LinearLayoutManager(this));
        pictureList.setAdapter(adapter);
    }

    private void getServiceCallSpecificDate(String specificDate) {
        if (!Util.isConnectedToNetwork(this)){
            fetchDataOverDb();
        } else {
            Call<CurrentDateResponse> call = apiInterface.currentDatePic(specificDate);
            call.enqueue(new Callback<CurrentDateResponse>() {
                @Override
                public void onResponse(Call<CurrentDateResponse> call, Response<CurrentDateResponse> response) {
                    progressbar.setVisibility(View.GONE);
                    CurrentDateResponse currentDateResponse = response.body();
                    currentDateResponse.setFavouriteFlag(false);
                    listPicData.add(currentDateResponse);
                    setRecyclerViewData("online");
                }

                @Override
                public void onFailure(Call<CurrentDateResponse> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void fetchDataOverDb() {
        progressbar.setVisibility(View.GONE);
        //List<CurrentDateResponse> listData = new ArrayList<>();
        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<PictureInfo> pictureInfo = DataBaseBuilder.getInstance(MainActivity.this).AppDataBase().pictureInfoDao().getAllPictureData();
                for(PictureInfo info : pictureInfo){
                    CurrentDateResponse data = new CurrentDateResponse();
                    data.setExplanation(info.getExplanation());
                    data.setDate(info.getDate());
                    data.setImageUrl(info.getUrl());
                    data.setTitle(info.getTitle());
                    data.setFavouriteFlag(info.getFlag());
                    listPicData.add(data);
                }
            }
        });

        setRecyclerViewData("offline");

        //Toast.makeText(this,"Please check the connectivity",Toast.LENGTH_SHORT).show();
    }

    private void getServiceCallDateRange(String dateFrom, String dateTo) {
        if (!Util.isConnectedToNetwork(this)){
            fetchDataOverDb();
            //Toast.makeText(this,"Please check the conectivity",Toast.LENGTH_SHORT).show();
        } else {
            Call<List<CurrentDateResponse>> call = apiInterface.dateRangePic(dateFrom, dateTo);
            call.enqueue(new Callback<List<CurrentDateResponse>>() {
                @Override
                public void onResponse(Call<List<CurrentDateResponse>> call, Response<List<CurrentDateResponse>> response) {
                    progressbar.setVisibility(View.GONE);
                    listPicData = response.body();
                    for (CurrentDateResponse data : listPicData){
                        data.setFavouriteFlag(false);
                    }
                    setRecyclerViewData("online");
                }

                @Override
                public void onFailure(Call<List<CurrentDateResponse>> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);
                }
            });
        }
    }
}