package com.parse.starter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.parse.starter.R;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsersFragment;

import java.lang.reflect.Array;
import java.util.HashMap;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private int[] icon = new int[]{R.drawable.ic_action_home, R.drawable.ic_people};
    private int iconSize;
    private HashMap<Integer, Fragment> utilityFragment;

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double scale = this.context.getResources().getDisplayMetrics().density;
        iconSize = (int) (36 * scale);
        this.utilityFragment = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
       Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                utilityFragment.put(position, fragment);
                break;
            case 1:
                fragment = new UsersFragment();
                break;
        }
        return fragment;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        utilityFragment.remove(position);
    }

    public Fragment getFragment(Integer index){
        return utilityFragment.get(index);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(this.context, icon[position]);
        drawable.setBounds(0,0,iconSize,iconSize);

        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public int getCount() {
        return icon.length;

    }
}