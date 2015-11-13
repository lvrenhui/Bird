package com.ninegame.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ninegame.bird.R;
import com.ninegame.bird.fragment.FragmentFactory;
import com.ninegame.bird.fragment.UserFragment;
import com.ninegame.bird.service.MyService;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_service_start).setOnClickListener(this);
        findViewById(R.id.btn_service_stop).setOnClickListener(this);
        findViewById(R.id.btn_service_bind).setOnClickListener(this);
        findViewById(R.id.btn_broadcat_send).setOnClickListener(this);
        findViewById(R.id.btn_fragment_start).setOnClickListener(this);

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
            case R.id.btn_broadcat_send:
                sendBroad();
                break;
            case R.id.btn_fragment_start:
                startFragment();
                break;
            default:
                break;


        }
    }

    private void startFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragmentType", FragmentFactory.ONE_FRAGMENT);
        startActivity(intent);

    }

    private void sendBroad() {
        Intent intent = new Intent("myreceiver");
        intent.putExtra("name", "lvrh");
        sendBroadcast(intent, "mypermission");
//        sendOrderedBroadcast(intent,"mypermission");
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
