package com.ninegame.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.ninegame.bird.R;
import com.ninegame.bird.fragment.FragmentFactory;
import com.ninegame.bird.framework.BaseFragment;

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
        pushFragment(intent.getIntExtra("fragmentType", 0), LAYOUT_CONTAINER, null);

    }

    public void pushFragment(int type, int container, Bundle args) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fT = fragmentManager.beginTransaction();

        Fragment fragment = null;
        fragment = FragmentFactory.getFragmentByType(type);
        if (fragment == fragmentManager.findFragmentById(container)) {
            return;
        }

        if (fragment != null) {
            if (args != null) {
                ((BaseFragment) fragment).setBundleArguments(args);
            }

            fT.replace(container, fragment, String.valueOf(type));
            fT.addToBackStack(String.valueOf(type));
        }

        fT.commitAllowingStateLoss();
    }
}
