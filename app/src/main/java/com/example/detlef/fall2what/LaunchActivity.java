package com.example.detlef.fall2what;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    private static SharedPreferences preferences;
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        /*
            Bisogna eseguire questo codice una sola volta per aggiungere
            nuove variabili alle Preferenze,
            Non so se facendolo IO puoi evitare di farlo TU o no;
         */

        /*
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("MyKey");
        editor.putString("LastChar", "char_0");
        editor.putInt("UnlockedChars", 1);
        editor.commit();
         */

        setupCharacterImageView();

        (findViewById(R.id.playButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this, ConnectionActivity.class);
                startActivity(intent);

            }
        });

        (findViewById(R.id.settingsButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this, SettingsActivity.class);
                startActivity(intent);

            }
        });

        (findViewById(R.id.currentPgView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this, ChoosePgActivity.class);
                startActivityForResult(intent, ChoosePgActivity.CHOOSE_PG_CODE);

            }
        });

        (findViewById(R.id.leaderButton)).setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)

            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(LaunchActivity.this, findViewById(R.id.leaderButton));
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

    private void setupCharacterImageView() {

        ImageView im = (ImageView) findViewById(R.id.currentPgView);

        String lastChar = preferences.getString("LastChar", "char_0");

        Resources res = getResources();
        int resourceId = res.getIdentifier(
                lastChar, "drawable", LaunchActivity.PACKAGE_NAME);
        im.setImageResource(resourceId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView im = (ImageView) findViewById(R.id.currentPgView);

        String lastChar = getSharedPreferences("myPreferences", Context.MODE_PRIVATE).getString("LastChar", "char_0");

        Log.e("gg", lastChar);

        Resources res = getResources();
        int resourceId = res.getIdentifier(
                lastChar, "drawable", LaunchActivity.PACKAGE_NAME);
        im.setImageResource(resourceId);
    }
}
