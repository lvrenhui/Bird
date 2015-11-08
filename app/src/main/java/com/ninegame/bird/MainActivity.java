package com.ninegame.bird;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.btn_service_start)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_service_stop)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_service_bind)).setOnClickListener(this);


        int tid = this.getTaskId();

        ((TextView) findViewById(R.id.activity_id)).setText("" + tid);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_service_start:
                openService();
                break;
            case R.id.btn_service_stop:
                stopService();
                break;
            case R.id.btn_service_bind:
                bindService();
                break;
            default:
                break;


        }
    }


    private void stopService() {
        stopService(new Intent(this, MyService.class));
    }

    private void openService() {
        startService(new Intent(this, MyService.class));
    }

    private void bindService() {
        openActivity();
    }

    void openActivity() {
//        Intent intent = new Intent(this, SecondActivity.class);
        Intent intent = new Intent("myaction");
        Bundle bundle = new Bundle();
        bundle.putString("name", "hui");
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), "code:" + resultCode, Toast.LENGTH_SHORT).show();
    }
}
