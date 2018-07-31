package com.lygedi.android.mobiletally.util;

import android.app.Application;

import org.mobile.library.global.ApplicationAttribute;
import org.mobile.library.global.Global;

/**
 * MyApplication
 *
 * @author sh
 * @version 1.0 2018/4/9
 * @since 1.0
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Global
        Global.init(this);

        //配置系统参数
        ApplicationAttribute.create().requestSign(true).appCode(StaticValue.APP_CODE).appToken
                (StaticValue.APP_TOKEN).loginUrl(StaticValue.Url.HTTP_LOGIN_URL);
    }
}
