package com.example.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DemoBoundServiceTinhTong extends Service {
    String TAG = "zzzzzzzzzzzzzzz";
    IBinder iBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: Gắn service....");
        return null;
    }

    public int Tong (int a, int b) {
        Log.d(TAG, "Tong: GỌi hàm tính tổng trong service");
        int t = a + b;
        return t;
    }

    public class LocalBinder extends Binder {
        LocalBinder getLocalBinder() {
            Log.d(TAG, "getLocalBinder: Khởi tạo Binder");
            return LocalBinder.this;
        }
    }
}
