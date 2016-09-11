package com.xzh.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.xzh.permissiondemo.base.BasePermissionActivity;

public class MainActivity extends BasePermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        request1();
    }

    private void request1() {
        requestPermission(new String[]{Manifest.permission.CAMERA}, new PermissionHandler() {
            @Override
            public void onGranted() {
                Intent intent = new Intent(); //调用照相机
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }

            @Override
            public void onDenied() {
                Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void request2() {
        requestPermission(new String[]{Manifest.permission.CAMERA}, new PermissionHandler() {
            @Override
            public void onGranted() {
                Intent intent = new Intent(); //调用照相机
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }

            @Override
            public void onDenied() {
                Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
