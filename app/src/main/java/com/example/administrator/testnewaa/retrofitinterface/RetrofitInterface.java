package com.example.administrator.testnewaa.retrofitinterface;

import com.example.administrator.testnewaa.bean.CityBean;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitInterface {


    @POST("pid=2")
    Call<CityBean> getCityData();

}
