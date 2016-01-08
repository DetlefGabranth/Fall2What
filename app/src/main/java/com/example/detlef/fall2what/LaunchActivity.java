package com.example.detlef.fall2what;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_launch);

        (findViewById(R.id.play_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this, ConnectionActivity.class);
                startActivity(intent);

            }
        });

        (findViewById(R.id.settings_image_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(LaunchActivity.this, SettingsActivity.class);
                //startActivity(intent);

            }
        });

        (findViewById(R.id.leader_image_button)).setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)

            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(LaunchActivity.this, findViewById(R.id.leader_image_button));
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_comedy:
                                Toast.makeText(LaunchActivity.this, "Comedy Clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item_movies:
                                Toast.makeText(LaunchActivity.this, "Movies Clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item_music:
                                Toast.makeText(LaunchActivity.this, "Music Clicked", Toast.LENGTH_SHORT).show();
                                return true;

                            default: return false;
                        }
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_settings_layout, popupMenu.getMenu());
                popupMenu.show();

            }
        });
        }
    }
