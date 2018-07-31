package com.lygedi.android.mobiletally.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.adapter.ViewPagerAdapter;

import org.mobile.library.common.function.ToolbarInitialize;

/**
 * 进出口理货Activity
 *
 * @author sh
 * @version 1.0 2018/5/2
 * @since 1.0
 */
public class InOutTallyActivity extends AppCompatActivity {


    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "InOutTallyActivity.";

    /**
     * 滑动分页
     */
    private ViewPager viewPager = null;

    /**
     * ViewPager的Fragment适配器
     */
    private ViewPagerAdapter viewPagerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inout_tally);

        //初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

    }


    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.inout_tally, true, true);
        // 初始化ViewPager和导航栏
        initViewPagerAndTab();

    }

    /**
     * 初始化ViewPager和导航栏
     */
    private void initViewPagerAndTab() {
        // 导航栏
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_out_tally, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}
