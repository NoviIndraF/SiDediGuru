package com.nifcompany.sidediguru.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nifcompany.sidediguru.Fragment.FragmentClassDetailQuestion;
import com.nifcompany.sidediguru.Fragment.FragmentClassDetailStudent;
import com.nifcompany.sidediguru.R;

public class AdapterTabClassDetail extends FragmentPagerAdapter {

    private final Context mContext;
    Bundle bundle;

    public AdapterTabClassDetail(Context context, FragmentManager fm, Bundle bundle) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_class_datail_1,
            R.string.tab_text_class_datail_2
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentClassDetailStudent();
                fragment.setArguments(bundle);

                break;
            case 1:
                fragment = new FragmentClassDetailQuestion();
                fragment.setArguments(bundle);
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
        return 2;
    }
}