package com.ninegame.bird.framework;

/**
 * Created by lvrh on 15/11/13.
 */
public class EnvObj {
    private Environment env = null;

    public EnvObj() {
    }

    public final void setEnvironment(Environment var1) {
        this.env = var1;
    }

    public final Environment getEnv() {
        return this.env;
    }
}
