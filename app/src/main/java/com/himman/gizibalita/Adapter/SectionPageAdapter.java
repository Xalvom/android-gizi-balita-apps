package com.himman.gizibalita.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.himman.gizibalita.Fragment.BbtbFragment;
import com.himman.gizibalita.Fragment.BbuFragment;
import com.himman.gizibalita.Fragment.TbuFragment;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.R;

public class SectionPageAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private String balita;

    public SectionPageAdapter(Context context, FragmentManager fm, String balita){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mContext = context;
        this.balita = balita;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.bbu,
            R.string.tbu,
            R.string.bbtb
    };
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new BbuFragment();
                Bundle bundleBbu = new Bundle();
                bundleBbu.putString(BbuFragment.EXTRA_BBU, balita);
                fragment.setArguments(bundleBbu);
                break;
            case 1:
                fragment = new TbuFragment();
                Bundle bundleTbu = new Bundle();
                bundleTbu.putString(TbuFragment.EXTRA_TBU, balita);
                fragment.setArguments(bundleTbu);
                break;
            case 2:
                fragment = new BbtbFragment();
                Bundle bundleBbus = new Bundle();
                bundleBbus.putString(BbtbFragment.EXTRA_BBTB, balita);
                fragment.setArguments(bundleBbus);
                break;
        }
        return fragment;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        return 3;
    }
}
