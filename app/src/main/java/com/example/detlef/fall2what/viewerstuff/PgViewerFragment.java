package com.example.detlef.fall2what.viewerstuff;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.detlef.fall2what.ChoosePgActivity;
import com.example.detlef.fall2what.R;

/**
 * Created by Altair07 on 09/01/2016.
 */
public class PgViewerFragment extends Fragment {

    public static Fragment newInstance(ChoosePgActivity context, int pos,
                                       float scale)
    {
        Bundle b = new Bundle();
        b.putInt("pos", pos);
        b.putFloat("scale", scale);
        return Fragment.instantiate(context, PgViewerFragment.class.getName(), b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        LinearLayout l = (LinearLayout)
                inflater.inflate(R.layout.viewer_fragment, container, false);

        int pos = this.getArguments().getInt("pos");

        TextView tv = (TextView) l.findViewById(R.id.choosePg_nameLabel);
        tv.setText("Position = " + pos);

        ImageView im = (ImageView) l.findViewById(R.id.choosePg_charImage);

        Resources res = getResources();
        int resourceId = res.getIdentifier(
                "char_" + pos, "drawable", ChoosePgActivity.PACKAGE_NAME);
        im.setImageResource(resourceId);

        PgViewerLinearLayout root = (PgViewerLinearLayout) l.findViewById(R.id.choosePg_rootLayout);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return l;
    }
}
