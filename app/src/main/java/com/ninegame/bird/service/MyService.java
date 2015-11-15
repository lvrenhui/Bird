package com.ninegame.bird.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ninegame.bird.tool.LogTool;

public class MyService extends Service {
    public static final String ACT = "com.ninegame.bird.MyService";

    private Binder myBinder = null;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LogTool.getTag(this), "serice create");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LogTool.getTag(this), "serice cmd start");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(LogTool.getTag(this), "bind service");
        if (myBinder == null) {
            myBinder = new MyBinder();
        }
        return myBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LogTool.getTag(this), "service stop");
    }

    public class MyBinder extends Binder {
        //此方法是为了可以在Acitity中获得服务的实例
        public MyService getService() {
            return MyService.this;
        }

    }
}
