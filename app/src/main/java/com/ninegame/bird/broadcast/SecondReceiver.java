package com.ninegame.bird.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ninegame.bird.tool.LogTool;

public class SecondReceiver extends BroadcastReceiver {
    public SecondReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LogTool.getTag(this), "second receive broadcast:" + intent.getStringExtra("name"));
    }
}
