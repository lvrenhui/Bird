package com.ninegame.bird.framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by lvrh on 15/11/13.
 */
public interface Environment {

    Activity getCurrentActivity();

    void setCurrentActivity(Activity var1);

    Context getApplicationContext();

    void setApplicationContext(Context var1);


    void startFragment(String className, Bundle bundle);

}