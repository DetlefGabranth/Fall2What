package com.example.detlef.fall2what.viewerstuff;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.detlef.fall2what.ChoosePgActivity;
import com.example.detlef.fall2what.R;

/**
 * Created by Altair07 on 09/01/2016.
 */
public class PgViewerAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener{

    private PgViewerLinearLayout cur = null;
    private PgViewerLinearLayout next = null;
    private ChoosePgActivity context;
    private FragmentManager fm;
    private float scale;

    public PgViewerAdapter(ChoosePgActivity context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        // make the first pager bigger than others
        if (position == ChoosePgActivity.FIRST_PAGE)
            scale = ChoosePgActivity.BIG_SCALE;
        else
            scale = ChoosePgActivity.SMALL_SCALE;

        position = position % ChoosePgActivity.PAGES;
        return PgViewerFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount()
    {
        return ChoosePgActivity.PAGES * ChoosePgActivity.LOOPS;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels)
    {
        if (positionOffset >= 0f && positionOffset <= 1f)
        {
            cur = getRootView(position);
            cur.setScaleBoth(ChoosePgActivity.BIG_SCALE - ChoosePgActivity.DIFF_SCALE * positionOffset);

            if (position < ChoosePgActivity.PAGES-1) {
                next = getRootView(position +1);
                next.setScaleBoth(ChoosePgActivity.SMALL_SCALE + ChoosePgActivity.DIFF_SCALE * positionOffset);
            }
        }
    }

    @Override
    public void onPageSelected(int position) 
    {
        Log.e("message","prova " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) 
    {}

    private PgViewerLinearLayout getRootView(int position)
    {
        return (PgViewerLinearLayout)
                fm.findFragmentByTag(this.getFragmentTag(position))
                        .getView().findViewById(R.id.choosePg_rootLayout);
    }

    private String getFragmentTag(int position)
    {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }
}
