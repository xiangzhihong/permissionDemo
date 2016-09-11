package com.xzh.permissiondemo.base;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xzh.permissiondemo.utils.PermissionUtils;

/**
 * Created by zhihong on 2016/9/11.
 */
public class BasePermissionActivity extends AppCompatActivity {

    private PermissionHandler mHandler=null;
    private static int requesrCode=001;

    /**
     * 请求权限
     */
    protected void requestPermission(String[] permissions, PermissionHandler handler) {
        if (PermissionUtils.hasSelfPermissions(this, permissions)) {
            handler.onGranted();
        } else {
            mHandler = handler;
            ActivityCompat.requestPermissions(this, permissions, requesrCode);
        }
    }


    protected void requestPermission( PermissionHandler handler,String permissions) {
        if (PermissionUtils.hasSelfPermissions(this, permissions)) {
            handler.onGranted();
        } else {
            mHandler = handler;
            ActivityCompat.requestPermissions(this, new String[]{permissions} , requesrCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mHandler == null) return;
        if (PermissionUtils.checkPermissions(grantResults)) {
            mHandler.onGranted();
        } else {
            if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
                if (!mHandler.onNeverRequest()) {
                    Toast.makeText(this, "权限已被拒绝,请在设置-应用-权限中打开", Toast.LENGTH_SHORT).show();
                }

            } else {
                mHandler.onDenied();
            }
        }
    }


    public abstract class PermissionHandler {
        //权限通过
        public abstract void onGranted();

        //权限拒绝
        public void onDenied() {
        }

        //不再询问
        public boolean onNeverRequest() {
            return false;
        }
    }
}
