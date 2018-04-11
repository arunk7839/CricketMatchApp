package com.example.lenovo.cricketmatchapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.cricketmatchapp.R;
import com.example.lenovo.cricketmatchapp.adapter.MatchCalenderAdapter;
import com.example.lenovo.cricketmatchapp.model.CalenderResponse;
import com.example.lenovo.cricketmatchapp.model.MatchCalender;
import com.example.lenovo.cricketmatchapp.rest.ApiClient;
import com.example.lenovo.cricketmatchapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "kAv1Vdcj8teYg5QnVmAACIofzdg2";
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.match_calender_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CalenderResponse> call = apiService.getMatchCalender(API_KEY);
        call.enqueue(new Callback<CalenderResponse>() {
            @Override
            public void onResponse(Call<CalenderResponse> call, Response<CalenderResponse> response) {
                int statuscode = response.code();
                List<MatchCalender> matchlist = response.body().getData();
                recyclerView.setAdapter(new MatchCalenderAdapter(matchlist, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<CalenderResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}