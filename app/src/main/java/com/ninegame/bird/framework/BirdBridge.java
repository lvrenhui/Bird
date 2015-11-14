package com.ninegame.bird.framework;

import com.ninegame.bird.fragment.FirstFragment;
import com.ninegame.bird.fragment.SecondFragment;

/**
 * Created by lvrh on 15/11/14.
 */
public class BirdBridge extends Bridge {

    @Override
    protected void initBindFragmentTypeAndName() {
        bindFragmentTypeAndName(FragmentFactory.ONE_FRAGMENT, FirstFragment.class.getName());
        bindFragmentTypeAndName(FragmentFactory.TWO_FRAGMENT, SecondFragment.class.getName());
    }
}
