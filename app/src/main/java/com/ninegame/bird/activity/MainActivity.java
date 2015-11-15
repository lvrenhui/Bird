package com.ninegame.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ninegame.bird.R;
import com.ninegame.bird.framework.FragmentFactory;
import com.ninegame.bird.framework.BaseFragment;
import com.ninegame.bird.tool.Tool;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    protected final static int LAYOUT_CONTAINER = android.R.id.content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empity);

        handleIntent(getIntent());

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String className = intent.getStringExtra("fragmentName");
        Log.w(Tool.getTag(this), className);

        initFragment(className, intent);

    }

    private void initFragment(String className, Intent intent) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            Object o = Class.forName(className).newInstance();
            BaseFragment baseFragment = (BaseFragment) o;
            if (fragmentManager.findFragmentById(LAYOUT_CONTAINER) == null) {
                pushFragment(baseFragment, null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void pushFragment(BaseFragment fragment, Bundle args) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fT = fragmentManager.beginTransaction();

        if (fragment == getCurrentFragment()) {
            return;
        }

        if (fragment != null) {
            if (args != null) {
                ((BaseFragment) fragment).setBundleArguments(args);
            }
            String className = fragment.getClass().getName();
            fT.replace(LAYOUT_CONTAINER, fragment, className);
            fT.addToBackStack(className);
        }

//        fT.commitAllowingStateLoss();
        fT.commit();
    }


    @Override
    public void onBackPressed() {
        Fragment baseFragment = getCurrentFragment();
        if (baseFragment instanceof BaseFragment) {
            if (((BaseFragment) baseFragment).goBack()) {
                return;
            }
        }

        popFragment();
    }

    public Fragment getCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(LAYOUT_CONTAINER);
        return fragment;
    }

    public void popFragment() {
        /* 解决fragment addToBackStack后，按返回键出现空白的Activity问题 */
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();

        } else {
            try {
                super.onBackPressed();//FIXME : 这里不应该用onBackPressed来关闭页面
            } catch (Exception e) {
                Log.w(Tool.getTag(this), e);
            }
        }
    }
}
