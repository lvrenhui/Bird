package com.ninegame.bird.framework;

import android.support.v4.app.Fragment;

import com.ninegame.bird.fragment.FirstFragment;
import com.ninegame.bird.fragment.SecondFragment;

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
                fragment = new FirstFragment();
                break;
            case TWO_FRAGMENT:
                fragment = new SecondFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    public static int getFragmentTypeByName(String className) {
        int type = 0;
        switch (className) {
            case "FirstFragment":
                type = ONE_FRAGMENT;
                break;
            case "TWO_FRAGMENT":
                type = TWO_FRAGMENT;
                break;
            default:
                break;
        }
        return type;
    }
}
