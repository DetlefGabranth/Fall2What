package com.example.detlef.fall2what;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        final Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                start.animate().translationX(-200);
            }
        });

        View.OnClickListener connectionListener = new View.OnClickListener()
        {

            boolean isWiFiSelected = true;
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
}
