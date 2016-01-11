package com.example.detlef.fall2what;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.detlef.fall2what.viewerstuff.PgViewerAdapter;

public class ChoosePgActivity extends FragmentActivity implements View.OnClickListener {

    public final static int CHOOSE_PG_CODE = 0;
    public final static int CHOOSE_PG_CONFIRM_CODE = 1;

    public final static int PAGES = 5;
    public final static int LOOPS = 1;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.8f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    private int choosenPosition;

    public PgViewerAdapter adapter;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pg);

        choosenPosition = 0;

        pager = (ViewPager) findViewById(R.id.choosePg_viewer);
        adapter = new PgViewerAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        //setOnPageChangeListener(adapter);
        pager.addOnPageChangeListener(adapter);

        /*
            Sarebbe opportuno aggiungere una Shared Preference per l'indice
            del LastChar scelto?
         */
        //pager.setCurrentItem(FIRST_PAGE);
        pager.setCurrentItem(choosenPosition);

        pager.setOffscreenPageLimit(3);

        //pager.setPageMargin(-200);
        pager.setPageMargin(-600);

        Button confirm = (Button) findViewById(R.id.choosePg_confirmButton);
        confirm.setOnClickListener(this);
    }

    public int getChoosenPosition()
    {
        return choosenPosition;
    }

    public void setChoosenPosition(int pos)
    {
        choosenPosition = pos;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.choosePg_confirmButton)
        {
            SharedPreferences.Editor editor = getSharedPreferences("myPreferences", Context.MODE_PRIVATE).edit();
            editor.putString("LastChar","char_"+choosenPosition);
            editor.commit();

            Log.e("gg", "char_"+choosenPosition);

            Intent i = new Intent();
            setResult(ChoosePgActivity.CHOOSE_PG_CONFIRM_CODE, i);
            this.finish();
        }
    }
}
