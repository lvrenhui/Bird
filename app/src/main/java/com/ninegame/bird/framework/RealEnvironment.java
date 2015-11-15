package com.ninegame.bird.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ninegame.bird.activity.MainActivity;

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
    public void startFragment(String className, Bundle bundle) {
        //tmp code
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fragmentName", className);

        try {
            Object o = Class.forName(className).newInstance();
            BaseFragment baseFragment = (BaseFragment) o;
            ((MainActivity) getCurrentActivity()).pushFragment(baseFragment, bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}
