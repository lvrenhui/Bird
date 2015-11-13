package com.ninegame.bird.framework;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by lvrh on 15/11/13.
 */
public final class FrameworkFacade {
    private static FrameworkFacade frameworkFacade = null;
    private boolean initialed = false;
    private EnvObj env;

    private FrameworkFacade() {
    }

    public static FrameworkFacade getInstance() {
        if (frameworkFacade == null) {
//            Class var0 = FrameworkFacade.class;
            synchronized (FrameworkFacade.class) {
                if (frameworkFacade == null) {
                    frameworkFacade = new FrameworkFacade();
                }
            }
        }

        return frameworkFacade;
    }

    public final void start(Context context) {
        if (!this.initialed) {
            this.initialed = true;
            if (context != null) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    throw new RuntimeException("Framework must be started in UI Thread");
                } else {
                    this.env = new EnvObj();
                    RealEnvironment realEnvironment = new RealEnvironment();
                    realEnvironment.setApplicationContext(context);
                    this.env.setEnvironment(realEnvironment);

                }
            }
        }
    }

    public final Environment getEnvironment() {
        return this.env != null ? this.env.getEnv() : null;
    }
}

