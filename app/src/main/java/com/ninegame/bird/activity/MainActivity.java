package com.ninegame.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ninegame.bird.R;
import com.ninegame.bird.framework.BaseFragment;
import com.ninegame.bird.tool.LogTool;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    protected final static int LAYOUT_CONTAINER = R.id.container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAppBar();
        handleIntent(getIntent());

    }


    private void initAppBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        toolbar.setTitle("Bird");
        toolbar.setSubtitle("little bird fly");
        toolbar.setNavigationIcon(R.drawable.left_arrow);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String className = intent.getStringExtra("fragmentName");
        Log.w(LogTool.getTag(this), className);

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
                Log.w(LogTool.getTag(this), e);
            }
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "yes,menu item", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 显示OverflowMenu的Icon
     *
     * @param featureId
     * @param menu
     */
    private void setOverflowIconVisible(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.d("OverflowIconVisible", e.getMessage());
                }
            }
        }
    }
}
