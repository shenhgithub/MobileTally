package com.lygedi.android.mobiletally.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lygedi.android.mobiletally.R;

/**
 * 进口理货页面
 *
 * @author sh
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class InTallyFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_in_tally, container, false);

        // 开启追加菜单项
        setHasOptionsMenu(true);



        TextView textView = (TextView) getActivity().findViewById(R.id.toolbar_title);
        textView.setText("NH27/1724S/I");


        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_in_tally, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_in_tally_move_bay:
                // 调贝

                return true;
            case R.id.menu_in_tally_revoke:
                // 撤销

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(getContext(), R.string.voyage_select_success, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Toast.makeText(getContext(), R.string.voyage_select_success, Toast.LENGTH_LONG).show();

    }
}
