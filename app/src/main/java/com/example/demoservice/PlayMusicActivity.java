package com.example.demoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PlayMusicActivity extends AppCompatActivity {

    String TAG = "zzzzzzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        Log.d(TAG, "onCreate: Khởi động activity Music");

        Log.d(TAG, "onCreate: Khởi động service từ activity");

        Intent i = new Intent(this, DemoService.class);
        startService( i );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Hủy Activity.");

    }
}