package com.ninegame.bird.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ninegame.bird.tool.Tool;

public class FirstReceiver extends BroadcastReceiver {
    public FirstReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(Tool.getTag(this), "first receive broadcast:" + intent.getStringExtra("name"));
        abortBroadcast();
    }
}
