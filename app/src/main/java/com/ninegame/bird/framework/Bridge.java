package com.ninegame.bird.framework;

import android.util.SparseArray;

/**
 * Created by lvrh on 15/11/14.
 */
public abstract class Bridge {
    private static SparseArray<String> gFragmentMap = new SparseArray<String>();

    /**
     * 绑定fragmentType和Fragment类，用于新框架Fragment跳转
     */
    protected abstract void initBindFragmentTypeAndName();

    protected void bindFragmentTypeAndName(int fragmentType, String fragmentName) {
        gFragmentMap.put(fragmentType, fragmentName);
    }

}
