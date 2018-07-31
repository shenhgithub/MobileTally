package com.lygedi.android.mobiletally.util;

/**
 * 全局常量
 *
 * @author sh
 * @version 1.0 2016/11/7
 * @since 1.0
 */
public interface StaticValue {

    /**
     * 应用代码
     */
    String APP_CODE = "JZXLH";

    /**
     * 应用令牌
     */
    String APP_TOKEN = "6D3F52486799E3D8E053A8640169E3D8";

    /**
     * 意图数据传递标签
     */
    interface IntentTag {
        /**
         * 贝位号选择取值标签
         */
        String BAYNUM_SELECT_TAG = "baynum_select_tag";


    }

    /**
     * 网络请求接口地址
     */
    interface Url {
        /**
         * IP
         */
        String HTTP_IP_URL = "http://www.boea.cn";

        /**
         * 登录
         */
        String HTTP_LOGIN_URL = HTTP_IP_URL + "/M_WL_Jzxlh/Service/Entrance/Login.aspx";

        /**
         * 获取派工航次
         */
        String HTTP_GET_ARRANGE_VOYAGES_URL = HTTP_IP_URL +
                "/M_WL_Jzxlh/Service/Voyage/GetArrangeVoyages.aspx";

        /**
         * 下载贝位规范
         */
        String HTTP_DOWNLOAD_BAY_STANARD_URL = HTTP_IP_URL +
                "/M_WL_Jzxlh/Service/Download/DownloadBayStandard.aspx";
    }

}
