package com.lygedi.android.mobiletally.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.fragment.InTallyFragment;
import com.lygedi.android.mobiletally.fragment.OutTallyFragment;

import org.mobile.library.global.Global;

/**
 * 主界面ViewPager适配器
 *
 * @author sh
 * @version 1.0 2018/5/2
 * @since 1.0
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InTallyFragment();
            case 1:
                return new OutTallyFragment();
            case 2:
                return new InTallyFragment();
            case 3:
                return new InTallyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Global.getContext().getString(R.string.view_pager_title_in_tally);
            case 1:
                return Global.getContext().getString(R.string.view_pager_title_out_tally);
            case 2:
                return Global.getContext().getString(R.string.view_pager_title_in_double_tally);
            case 3:
                return Global.getContext().getString(R.string.view_pager_title_out_double_tally);
        }
        return null;
    }
}
