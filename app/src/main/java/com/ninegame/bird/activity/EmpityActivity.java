package com.ninegame.bird.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ninegame.bird.R;
import com.ninegame.bird.fragment.UserFragment;
import com.ninegame.bird.tool.Tool;

public class EmpityActivity extends AppCompatActivity {

    static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empity);

        fm = getSupportFragmentManager();

//        String fName = getIntent().getStringExtra("name");
//        Log.i(Tool.getTag(this), fName);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.empity_activity, new UserFragment());
        ft.commit();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(Tool.getTag(this), intent.getStringExtra("name"));

    }
}
