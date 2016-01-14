package com.example.detlef.fall2what;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.detlef.fall2what.gamelogic.BluetoothConnector;

public class ConnectionActivity extends AppCompatActivity {

    public ListView listView;
    boolean isWiFiSelected = true;
    private BluetoothConnector btConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        final Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                findViewById(R.id.wifiButton).animate().translationX(-300);
                findViewById(R.id.blueButton).animate().translationX(-300);
                findViewById(R.id.imageView2).animate().translationX(-300);
                findViewById(R.id.mode1).animate().translationX(+300);
                findViewById(R.id.mode2).animate().translationX(+300);
                findViewById(R.id.imageView3).animate().translationX(+300);

                ((Button) findViewById(R.id.startButton)).setText("Start Game");
                findViewById(R.id.listView).setVisibility(View.VISIBLE);

                if (isWiFiSelected) {
                    //wifi stuff
                } else {


                    listView = ( ListView )findViewById(R.id.listView);
                    btConnector = new BluetoothConnector(ConnectionActivity.this);
                    btConnector.on();
                    btConnector.visible();
                    btConnector.list();

                }



            }
        });

        View.OnClickListener connectionListener = new View.OnClickListener()
        {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.wifiButton || v.getId() == R.id.blueButton ) {
                    switch (v.getId()) {
                        case R.id.wifiButton:
                            isWiFiSelected = true;
                            Log.e("Tag","wifi selezionato");
                            break;
                        case R.id.blueButton:
                            isWiFiSelected = false;
                            Log.e("Tag","bluet. selezionato");
                            break;
                        default:
                            throw new RuntimeException("Unknow button ID");
                    }
                }
            }
        };
        findViewById(R.id.wifiButton).setOnClickListener(connectionListener);
        findViewById(R.id.blueButton).setOnClickListener(connectionListener);


    }

    @Override
    public void onDestroy() {

        btConnector.disconnect();
        super.onDestroy();
    }

}
