package com.codermert.mvvmarchitecture.java;

import android.net.DnsResolver;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.codermert.mvvmarchitecture.commons.ApiInterface;
import com.codermert.mvvmarchitecture.commons.MyApplication;
import com.codermert.mvvmarchitecture.java.HolidayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayRepo {

    private final String TAG = getClass().getSimpleName();

    public androidx.lifecycle.MutableLiveData<List<HolidayModel>> requestHolidays() {
        final androidx.lifecycle.MutableLiveData<List<HolidayModel>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService =
                MyApplication.getRetrofitClient().create(ApiInterface.class);

        apiService.getHolidays().enqueue(new Callback<List<HolidayModel>>() {
            @Override
            public void onResponse(Call<List<HolidayModel>> call, Response<List<HolidayModel>> response) {
                Log.e(TAG, "getCurrencyList response="+response );

                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestHolidays response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<HolidayModel>> call, Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }

}
