package com.codermert.mvvmarchitecture.commons;


import com.codermert.mvvmarchitecture.java.HolidayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *Coder Mert
 */
public interface ApiInterface {

    @GET("PublicHolidays/2022/tr")
    Call<List<HolidayModel>> getHolidays();


}

