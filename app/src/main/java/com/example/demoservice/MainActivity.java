package com.example.demoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG = "zzzzzzzzzzzzzz";
    DemoBoundServiceTinhTong serviceTinhTong;
    // Tạo biến connection
    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // Kết nối
            Log.d(TAG, "onServiceConnected: Kết nối service thành công");
            DemoBoundServiceTinhTong.LocalBinder localBinder = (DemoBoundServiceTinhTong.LocalBinder) iBinder;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: Ngắt kết nối service");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi động activity
        startActivity(new Intent(this, PlayMusicActivity.class));
        // Tiếp theo vào manifests khai báo service
        // ========================================

        // Làm việc với BindService
        serviceTinhTong = new DemoBoundServiceTinhTong(); // giống như tạo đối tượng DAO
        Intent intentTinhTong = new Intent(MainActivity.this, DemoBoundServiceTinhTong.class);
        bindService(intentTinhTong, sv_conn, Context.BIND_AUTO_CREATE);

        // Lấy đối tượng và gọi lớp sử dụng
//        serviceTinhTong.Tong(); // cách dùng giống DAO

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn = findViewById(R.id.btnTong);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tong = serviceTinhTong.Tong(6, 10);
                Toast.makeText(serviceTinhTong, "Tổng = " + tong, Toast.LENGTH_SHORT).show();
            }
        });

        // chạy thử nghiệm ứng dụng
    }
}