package com.lygedi.android.mobiletally.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.adapter.MainFunctionRecyclerViewAdapter;
import com.lygedi.android.mobiletally.function.BayStandardFunction;
import com.lygedi.android.mobiletally.function.SettingFunction;
import com.lygedi.android.mobiletally.function.VoyageSelectFunction;
import com.lygedi.android.mobiletally.holder.MainFunctionItemViewHolder;

import org.mobile.library.common.function.CheckUpdate;
import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.global.Global;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import static com.lygedi.android.mobiletally.adapter.FunctionIndex.toFunction;

/**
 * 主界面
 *
 * @author sh
 * @version 1.0 2018/4/9
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MainActivity.";

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    /**
     * 航次选择数据功能类
     */
    private VoyageSelectFunction voyageSelectFunction = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件引用
        initViewHolder();

        // 加载界面
        initView();
    }

    @Override
    protected void onResume() {

        // 初始化功能布局
        initGridView();

        super.onResume();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.app_name, true, false);
        TextView titleTextView = (TextView) findViewById(R.id.toolbar_title);
        titleTextView.setText("理货员" + Global.getApplicationConfig().getUserName());

        // 初始化功能布局
        initGridView();

        //执行检查权限
        checkPermission();

        //        // 执行检查更新
        //        checkUpdate();

    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        voyageSelectFunction = new VoyageSelectFunction(this);

    }


    /**
     * 检查新版本
     */
    private void checkUpdate() {

        // 新建版本检查工具
        CheckUpdate checkUpdate = new CheckUpdate(this);
        // 执行检查
        checkUpdate.checkInBackground();
    }

    /**
     * 检查权限
     */
    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission
                                                                                      .WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, R.string.refuse_pmission, Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    /**
     * 初始化表格布局
     */
    private void initGridView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);

        // 创建布局管理器
        GridLayoutManager gridlayoutManager = new GridLayoutManager(this, 2);

        // 设置布局管理器
        recyclerView.setLayoutManager(gridlayoutManager);

        // 新建数据适配器
        MainFunctionRecyclerViewAdapter adapter = new MainFunctionRecyclerViewAdapter(this);

        // 设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<String[],
                MainFunctionItemViewHolder>() {
            @Override
            public void onClick(String[] dataSource, MainFunctionItemViewHolder holder) {
                onGridItemClick(holder.getAdapterPosition());
            }
        });

        // 绑定数据适配器
        recyclerView.setAdapter(adapter);
    }


    /**
     * 表格项点击事件触发操作，
     * 默认触发功能跳转，
     * 并检测登录状态
     *
     * @param position 点击的位置索引
     */
    private void onGridItemClick(int position) {

        if (!Global.getLoginStatus().isLogin()) {
            // 未登录
            // 新建意图,跳转到登录页面
            Intent intent = new Intent(this, LoginActivity.class);
            // 执行跳转
            startActivity(intent);
            finish();
            return;
        }

        if (position == 2 && voyageSelectFunction.onLoadSelectedVoyageFromDataBase() == null) {

            Toast.makeText(MainActivity.this, R.string.please_voyage_select, Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // 跳转到功能
        toFunction(this, position);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_logout:
                // 退出操作
                doLogout();
            case R.id.menu_chear:
                // 清除缓存
                doClear();
                break;
        }
        return true;
    }

    /**
     * 退出操作
     */
    private void doLogout() {

        // 清空保存记录
        Global.getApplicationConfig().setPassword("");
        Global.getApplicationConfig().Save();

        new SettingFunction(this).onClear();
        new VoyageSelectFunction(this).onClear();
        new BayStandardFunction(this).onClear();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 清除缓存
     */
    private void doClear() {
        new SettingFunction(this).onClear();
        new VoyageSelectFunction(this).onClear();
        new BayStandardFunction(this).onClear();
        onResume();
    }
}
