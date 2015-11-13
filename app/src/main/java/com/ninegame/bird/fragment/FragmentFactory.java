package com.ninegame.bird.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by lvrh on 15/11/14.
 */
public class FragmentFactory {
    public final static int ONE_FRAGMENT = 1;
    public final static int TWO_FRAGMENT = 2;

    public static Fragment getFragmentByType(int type) {
        Fragment fragment = null;
        switch (type) {
            case ONE_FRAGMENT:
                fragment = new UserFragment();
                break;
            case TWO_FRAGMENT:
                fragment = new SecondFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
