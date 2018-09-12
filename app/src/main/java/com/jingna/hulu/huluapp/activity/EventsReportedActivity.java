package com.jingna.hulu.huluapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingna.hulu.huluapp.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;

public class EventsReportedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_reported);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventsReportedActivity.this);

    }
}
