package com.lygedi.android.mobiletally.function;

import android.content.Context;
import android.util.Log;
import com.lygedi.android.mobiletally.bean.Voyage;
import com.lygedi.android.mobiletally.database.VoyageOperator;
import org.mobile.library.model.database.BaseOperator;

/**
 * 航次选择数据功能类
 *
 * @author sh
 * @version 1.1 2018/4/12
 * @since 1.0
 */

public class VoyageSelectFunction {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageSelectFunction.";

    /**
     * 上下文
     */
    private Context context = null;
    /**
     * 数据库操作工具
     */
    private VoyageOperator operator = null;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VoyageSelectFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        this.operator = (VoyageOperator) onCreateOperator(context);
    }


    /**
     * 创建数据库操作对象
     *
     * @param context 上下文
     * @return 数据库操作对象
     */
    private BaseOperator<Voyage> onCreateOperator(Context context) {
        return new VoyageOperator(context);
    }

    /**
     * 清空表
     */
    public void onClear() {
        Log.i(LOG_TAG + "onClear", "clear is invoked");
        operator.clear();
    }

    /**
     * 将已选择航次数据持久化到本地
     *
     * @param data 数据
     */
    public void onSaveSelectedVoyage(Voyage data){

        Log.i(LOG_TAG + "onSaveSelectedVoyage", "onSaveSelectedVoyage is invoked");
        Log.i(LOG_TAG + "onSaveSelectedVoyage", "Voyage data is " + data.getChi_vessel());

        if (operator != null&& data != null) {
            onClear();
            operator.insert(data);
        }

    }



    /**
     * 从数据库获取已选择航次
     *
     * @return 数据集合
     */
    public Voyage onLoadSelectedVoyageFromDataBase() {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database is null");
            return null;
        }

        return operator.querySelectedVoyage();
    }

}
