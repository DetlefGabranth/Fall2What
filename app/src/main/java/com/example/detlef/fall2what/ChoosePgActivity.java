package com.example.detlef.fall2what;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.detlef.fall2what.viewerstuff.PgViewerAdapter;

public class ChoosePgActivity extends FragmentActivity {

    public final static int PAGES = 5;
    public final static int LOOPS = 1;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.8f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    public static String PACKAGE_NAME;

    public PgViewerAdapter adapter;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pg);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        pager = (ViewPager) findViewById(R.id.choosePg_viewer);
        adapter = new PgViewerAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        //setOnPageChangeListener(adapter);
        pager.addOnPageChangeListener(adapter);

        //pager.setCurrentItem(FIRST_PAGE);
        pager.setCurrentItem(1);

        pager.setOffscreenPageLimit(3);

        //pager.setPageMargin(-200);
        pager.setPageMargin(-600);
    }


}
