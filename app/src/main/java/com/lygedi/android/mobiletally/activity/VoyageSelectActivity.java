package com.lygedi.android.mobiletally.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.adapter.ArrangeVoyagesRecyclerViewAdapter;
import com.lygedi.android.mobiletally.bean.BayStandard;
import com.lygedi.android.mobiletally.bean.Setting;
import com.lygedi.android.mobiletally.bean.Voyage;
import com.lygedi.android.mobiletally.function.BayStandardFunction;
import com.lygedi.android.mobiletally.function.SettingFunction;
import com.lygedi.android.mobiletally.function.VoyageSelectFunction;
import com.lygedi.android.mobiletally.holder.VoyageItemViewHolder;
import com.lygedi.android.mobiletally.work.PullArrangeVoyages;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.global.Global;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

import static android.widget.Toast.makeText;

/**
 * 航次选择Activity
 *
 * @author sh
 * @version 1.0 2018/4/12
 * @since 1.0
 */
public class VoyageSelectActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageSelectActivity.";

    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 在港航次堆存列表数据适配器
         */
        public ArrangeVoyagesRecyclerViewAdapter recyclerViewAdapter = null;

        /**
         * 上一个执行的加载任务
         */
        public volatile DefaultWorkModel beforeLoadWork = null;

        /**
         * 航次选择数据功能类
         */
        public VoyageSelectFunction voyageSelectFunction = null;

        /**
         * 设置数据功能类
         */
        public SettingFunction settingFunction = null;

        /**
         * 贝位规范数据功能类
         */
        public BayStandardFunction bayStandardFunction = null;

        /**
         * 下拉刷新控件
         */
        public SwipeRefreshLayout refreshLayout = null;
    }

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_select);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化数据
        initData();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {
        viewHolder = new LocalViewHolder();

        // 堆存列表适配器
        viewHolder.recyclerViewAdapter = new ArrangeVoyagesRecyclerViewAdapter(this);

        viewHolder.voyageSelectFunction = new VoyageSelectFunction(this);
        viewHolder.settingFunction = new SettingFunction(this);
        viewHolder.bayStandardFunction = new BayStandardFunction(this);

        viewHolder.refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_voyage_select_swipeRefreshLayout);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.voyage_select, true, true);
        // 初始化列表
        initListView();
        //初始化刷新控件
        initSwipeRefresh();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_voyage_select_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 设置点击事件
        viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {
            @Override
            public void onClick(List<Voyage> voyages, VoyageItemViewHolder voyageItemViewHolder) {

                viewHolder.recyclerViewAdapter.switchSelectedState(voyageItemViewHolder);
                Voyage voyage = voyages.get(voyageItemViewHolder.getLayoutPosition());

                //判断当前航次是否已下载船舶规范
                if (!viewHolder.bayStandardFunction.isDownloaded(voyage.getV_newId())) {
                    doDownload(voyage);
                }


            }
        });

        // 设置列表适配器
        recyclerView.setAdapter(viewHolder.recyclerViewAdapter);
    }

    /**
     * 初始化刷新控件
     */
    private void initSwipeRefresh() {
        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        viewHolder.refreshLayout.setColorSchemeResources(typedArray.getResourceId(0, 0));
        typedArray.recycle();

        viewHolder.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        loadData(true);
    }

    /**
     * 加载数据
     *
     * @param reload 表示是否为全新加载，true表示为全新加载
     */
    private void loadData(boolean reload) {
        Log.i(LOG_TAG + "loadData", "reload tag is " + reload);

        if (reload) {
            // 属于全新加载数据，清空原数据
            viewHolder.recyclerViewAdapter.clear();
            // 中断上次请求
            if (viewHolder.beforeLoadWork != null) {
                viewHolder.beforeLoadWork.cancel();
            }
        }

        // 堆存列表任务
        PullArrangeVoyages pullVoyageList = new PullArrangeVoyages();

        pullVoyageList.setWorkEndListener(new WorkBack<List<Voyage>>() {
            @Override
            public void doEndWork(boolean state, List<Voyage> data) {

                Log.i(LOG_TAG + "loadData", "state is " + state);
                Log.i(LOG_TAG + "loadData", "data is " + data);
                if (state) {
                    if (data != null) {

                        // 插入新数据
                        viewHolder.recyclerViewAdapter.addData(viewHolder.recyclerViewAdapter
                                .getItemCount(), data);
                    } else {
                        makeText(getBaseContext(), R.string.no_arrange_voyage, Toast
                                .LENGTH_SHORT).show();
                    }
                } else {

                    makeText(getBaseContext(), R.string.error_field_required, Toast.LENGTH_SHORT)
                            .show();
                }

                // 停止动画
                viewHolder.refreshLayout.setRefreshing(false);
            }
        });


        // 打开加载动画
        viewHolder.refreshLayout.setRefreshing(true);

        Setting setting = viewHolder.settingFunction.onLoadSettingFromDataBase();
        Log.i(LOG_TAG + "loadData", "setting.getCode_class() is " + setting.getCode_class());
        // 执行任务
        pullVoyageList.beginExecute(Global.getLoginStatus().getUserID(), setting.getDate(), "0" +
                setting.getCode_class());

        // 保存新的加载任务对象
        viewHolder.beforeLoadWork = pullVoyageList;
    }


    /**
     * 下载操作
     */
    private void doDownload(final Voyage voyage) {

        //不存在提示下载船舶规范
        new AlertDialog.Builder(VoyageSelectActivity.this).setTitle(voyage.getChi_vessel())
                .setMessage("下载船舶规范").setPositiveButton("确定", new DialogInterface.OnClickListener
                () {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                viewHolder.bayStandardFunction.SetDownloadEndListener(new WorkBack<List<BayStandard>>() {


                    @Override
                    public void doEndWork(boolean state, List<BayStandard> data) {

                        if (state && data != null) {
                            Log.i(LOG_TAG + "doDownload", "data count is " + data.size());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    stopProgressDialog();
                                    voyage.setSelected_mark(1);
                                    viewHolder.voyageSelectFunction.onSaveSelectedVoyage(voyage);
                                    makeText(getBaseContext(), R.string.download_success, Toast
                                            .LENGTH_SHORT).show();
                                }
                            });
                        } else if (state && data == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //停止进度条
                                    stopProgressDialog();
                                    Toast.makeText(getBaseContext(), R.string
                                            .no_bay_standard, Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //停止进度条
                                    stopProgressDialog();
                                    Toast.makeText(getBaseContext(), R.string
                                            .download_error_field_required, Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });
                        }
                    }
                });

                startProgressDialog();
                viewHolder.bayStandardFunction.onLoad(voyage.getV_newId());
            }
        }).show();
    }

    /**
     * 打开进度条
     */
    protected void startProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置提醒
            progressDialog.setMessage("数据正在下载中....");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * 停止进度条
     */
    protected void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }


}
