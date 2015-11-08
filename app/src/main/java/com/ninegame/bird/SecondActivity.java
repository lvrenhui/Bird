package com.ninegame.bird;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ((Button) findViewById(R.id.btn_open)).setOnClickListener(this);
        int tid = this.getTaskId();
        ((TextView) findViewById(R.id.activity_id)).setText("" + tid);
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, bundle.getString("name"), Toast.LENGTH_SHORT).show();
        setResult(2);
//        finish();


        bindService(new Intent(this, MyService.class), serviceconn, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(Tool.getTag(this), "service bind connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(Tool.getTag(this), "service bind disconnected");
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_open:
                openActivity();
                break;
            default:
                break;


        }
    }

    void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceconn);
        Log.i(Tool.getTag(this), "service unbind ");

    }
}
