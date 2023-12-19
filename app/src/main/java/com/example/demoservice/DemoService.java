package com.example.demoservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class DemoService extends Service {
    String TAG = "zzzzzzzz";
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: Gắn service .... ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Bắt đầu chạy công việc...");
        Log.d(TAG, "onStartCommand: Chạy nhạc chuông...");

/*
        player = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);    // chạy lặp lại nhiều lần
        player.start(); // bắt đầu chạy nhạc
*/
        // cải tiến: đóng phần music trên lại, tạo luồng riêng để chạy

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: Chạy nhạc");
                player = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
                player.setLooping(true);    // chạy lặp lại nhiều lần
                player.start(); // bắt đầu chạy nhạc

                // Thiết lập sau 1 phút thì tự tắt nhạc
                // Là cho thread này dừng 1 phút
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Đến đây là hết 60s
                player.stop();
                // Dừng service
                stopSelf();
            }
        });

        thread.start();

        return  START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Hủy service !!!");
    }
}
