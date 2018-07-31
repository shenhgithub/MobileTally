package com.lygedi.android.mobiletally.function;

import android.content.Context;
import android.util.Log;
import com.lygedi.android.mobiletally.bean.Setting;
import com.lygedi.android.mobiletally.database.SettingOperator;
import org.mobile.library.model.database.BaseOperator;

/**
 * 设置数据功能类
 *
 * @author sh
 * @version 1.1 2018/4/12
 * @since 1.0
 */

public class SettingFunction {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SettingFunction.";

    /**
     * 上下文
     */
    private Context context = null;
    /**
     * 数据库操作工具
     */
    private SettingOperator operator = null;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public SettingFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        this.operator = (SettingOperator) onCreateOperator(context);
    }


    /**
     * 创建数据库操作对象
     *
     * @param context 上下文
     * @return 数据库操作对象
     */
    private BaseOperator<Setting> onCreateOperator(Context context) {
        return new SettingOperator(context);
    }

    /**
     * 清空表
     */
    public void onClear() {
        Log.i(LOG_TAG + "onClear", "clear is invoked");
        operator.clear();
    }

    /**
     * 将设置数据持久化到本地
     *
     * @param data 数据
     */
    public void onSaveSetting(Setting data){

        Log.i(LOG_TAG + "onSaveSetting", "onSaveSelectedVoyage is invoked");

        if (operator != null&& data != null) {
            operator.clear();
            operator.insert(data);
        }

    }


    /**
     * 从数据库获取设置数据
     *
     * @return 数据集合
     */
    public Setting onLoadSettingFromDataBase() {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        return operator.querySetting();
    }

}
