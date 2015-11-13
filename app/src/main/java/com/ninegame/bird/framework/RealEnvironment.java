package com.ninegame.bird.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ninegame.bird.activity.MainActivity;
import com.ninegame.bird.fragment.FragmentFactory;

/**
 * Created by lvrh on 15/11/14.
 */
final class RealEnvironment implements Environment {
    private Activity activity;
    private Context context;

    RealEnvironment() {

    }

    @Override
    public Activity getCurrentActivity() {
        return activity;
    }

    @Override
    public void setCurrentActivity(Activity activity) {
        this.activity = activity;

    }

    @Override
    public Context getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(Context context) {
        this.context = context;

    }

    @Override
    public void startFragment(String var1, Bundle var2) {
        //tmp code
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fragmentType", FragmentFactory.TWO_FRAGMENT);
        context.startActivity(intent);

    }


}
