package com.lygedi.android.mobiletally.adapter;

import android.content.Context;
import android.content.Intent;

import com.lygedi.android.mobiletally.activity.InOutTallyActivity;
import com.lygedi.android.mobiletally.activity.SettingActivity;
import com.lygedi.android.mobiletally.activity.VoyageSelectActivity;

/**
 * 主界面功能索引
 *
 * @author sh
 * @version 1.0 2018/4/13
 * @since 1.0
 */
public class FunctionIndex {

    /**
     * 跳转到选中的功能界面
     *
     * @param context  上下文
     * @param position 功能索引位置，从0开始
     */
    public static void toFunction(Context context, int position) {

        // 功能页跳转
        Intent intent = null;

        switch (position) {
            case 0:
                // 设置
                intent = new Intent(context, SettingActivity.class);
                break;
            case 1:
                // 航次选择
                intent = new Intent(context, VoyageSelectActivity.class);
                break;
            case 2:
                // 进出口理货
                intent = new Intent(context, InOutTallyActivity.class);
                break;
            case 3:
                // 进度查询
                intent = new Intent(context, InOutTallyActivity.class);
                break;
            case 4:
                // 贝位图
                intent = new Intent(context, InOutTallyActivity.class);
                break;


        }

        if (intent != null) {
            // 执行跳转
            context.startActivity(intent);
        }
    }
}
