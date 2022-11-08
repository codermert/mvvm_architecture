package com.codermert.mvvmarchitecture.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codermert.mvvmarchitecture.R;
import com.codermert.mvvmarchitecture.commons.MyApplication;
import com.codermert.mvvmarchitecture.databinding.ActivityHolidayBinding;

import java.util.List;

public class HolidayActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    ActivityHolidayBinding binding;
    HolidayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_holiday);
        initUI();

        if(MyApplication.getInstance().isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);

            HolidayViewModel holidayViewModel = new HolidayViewModel();
            holidayViewModel.getHolidays().observe(this, new Observer<List<HolidayModel>>() {
                @Override
                public void onChanged(List<HolidayModel> currencyPojos) {
                    if (currencyPojos != null && !currencyPojos.isEmpty()) {
                        Log.e(TAG, "observe onChanged()=" + currencyPojos.size());
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.addHolidayList(currencyPojos);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        }else{
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }

    void initUI(){
        binding.rvHolidayList.setHasFixedSize(true);
        binding.rvHolidayList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HolidayAdapter();
        binding.rvHolidayList.setAdapter(adapter);
    }

}
