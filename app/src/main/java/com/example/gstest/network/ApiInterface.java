package com.example.gstest.network;

import com.example.gstest.model.CurrentDateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String api_key = "HhFUUdi1uU30kr5UMC8Hcl5bl32PqEOXDWK0Kekk";
    @GET("planetary/apod?api_key="+api_key)
    Call<CurrentDateResponse> currentDatePic(@Query("date") String currentDate);

    @GET("planetary/apod?api_key="+api_key)
    Call<List<CurrentDateResponse>> dateRangePic(@Query("start_date") String startDate, @Query("end_date") String endDate);
}
