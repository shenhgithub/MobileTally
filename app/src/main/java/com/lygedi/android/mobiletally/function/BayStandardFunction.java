package com.lygedi.android.mobiletally.function;

import android.content.Context;
import android.util.Log;

import com.lygedi.android.mobiletally.bean.BayStandard;
import com.lygedi.android.mobiletally.database.BayStandardOperator;
import com.lygedi.android.mobiletally.work.DownloadBayStandard;

import org.mobile.library.model.database.BaseOperator;
import org.mobile.library.model.work.IWorkEndListener;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 贝位规范数据功能类
 *
 * @author sh
 * @version 1.1 2018/06/25
 * @since 1.0
 */
public class BayStandardFunction {

    /**
     * 日志标签前戳
     */
    private static final String LOG_TAG="BayStandardFunction.";

    /**
     * 数据集合
     */
    private List<BayStandard> dataList=null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 数据库操作工具
     */
    private BayStandardOperator operator = null;

    /**
     * 标识是否正在加载数据
     */
    protected volatile boolean loading = false;

    /**
     * 标识工具加载是否被取消
     */
    protected volatile boolean canceled = false;

    /**
     * 任务结束回调接口
     */
    IWorkEndListener<List<BayStandard>>  iWorkEndListener = null;


    /**
     * 构造函数
     * @param context 上下文
     */
    public BayStandardFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        //创建数据库操作对象
        this.operator = new BayStandardOperator(context);
    }

    /**
     * 设置下载任务结束监听事件
     * @param iWorkEndListener 下载任务结束监听事件对象
     */
    public void SetDownloadEndListener(IWorkEndListener<List<BayStandard>> iWorkEndListener){
        this.iWorkEndListener = iWorkEndListener;
    }

    /**
     * 数据加载
     * @param parameter 取值条件参数
     */
    public void onLoad(String parameter){

        Log.i(LOG_TAG + "onLoad", "onLoad is invoked");

        //记载开始
        loading = true;
        dataList = null;

        if (!canceled && (dataList == null || dataList.size() == 0)) {
            // 从网络拉取
            Log.i(LOG_TAG + "onLoad", "from network");
            onLoadFromNetWork(parameter);
        } else {
            if (!canceled) {
                onNotify(context);
            }
            // 加载结束
            loading = false;
        }
    }

    /**
     * 从网络加载数据，
     * 完成请求后要调用{@link #netWorkEndSetData(boolean , List)}继续执行后续任务
     *
     * @param parameter 取值条件参数
     */
    private void onLoadFromNetWork(String parameter) {
        Log.i(LOG_TAG + "onLoadFromNetWork", "parameter is " + parameter);

        final DownloadBayStandard workModel = new DownloadBayStandard();

        workModel.setWorkEndListener(new WorkBack<List<BayStandard>>() {
            @Override
            public void doEndWork(boolean state, List<BayStandard> data) {
                if (state && data != null) {

                    Log.i(LOG_TAG + "netWorkEndSetData", "getResult is invoked");
                    netWorkEndSetData(state, data);
                } else {
                    iWorkEndListener.doEndWork(state, null, data);
                }

            }
        }, false);

        Log.i(LOG_TAG + "netWorkEndSetData", "beginExecute is invoked");
        workModel.beginExecute(parameter);
    }

    /**
     * 网络请求结束后调用
     *
     * @param state 网络任务执行结果
     * @param data  结果数据
     */
    private void netWorkEndSetData(boolean state, List<BayStandard> data) {
        Log.i(LOG_TAG + "netWorkEndSetData", "result is " + state);

        if (!canceled) {
            // 提取结果
            Log.i(LOG_TAG + "netWorkEndSetData", "onNetworkEnd is invoked");
            dataList = onNetworkEnd(state, data);
        }

        if (!canceled) {
            // 保存数据
            Log.i(LOG_TAG + "netWorkEndSetData", "onSaveData is invoked");
            onSaveData(operator, dataList);
        }

        if (!canceled) {
            onNotify(context);
        }
        // 加载结束
        loading = false;

        iWorkEndListener.doEndWork(state, null, data);
    }


    /**
     * 整理从服务器取回的数据
     *
     * @param state 执行结果
     * @param data  响应数据
     *
     * @return 整理好的数据集
     */
    private List<BayStandard> onNetworkEnd(boolean state, List<BayStandard> data) {
        return state ? data : null;
    }

    /**
     * 将服务器取回的数据持久化到本地
     *
     * @param operator 数据库操作类
     * @param dataList 数据集
     */
    private void onSaveData(BaseOperator<BayStandard> operator, List<BayStandard> dataList) {
        if (!canceled && operator != null && dataList != null) {
            this.operator.deleteBayStandard(dataList.get(0));
            operator.insert(dataList);
        }
    }

    /**
     * 数据加载结束发送广播通知
     */
    public void onNotify(Context context) {
    }


    /**
     * 清空表
     */
    public void onClear() {
        Log.i(LOG_TAG + "onClear", "clear is invoked");
        operator.clear();
    }

    /**
     * 是否已下载
     *
     * @param v_id 船舶编码
     *
     * @return true/false
     */
    public boolean isDownloaded(String v_id) {
        Log.i(LOG_TAG + "isDownloaded", "isDownloaded is invoked");

        return operator.isExistByV_id(v_id);
    }


}
