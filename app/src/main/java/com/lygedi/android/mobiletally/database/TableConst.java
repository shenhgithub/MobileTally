package com.lygedi.android.mobiletally.database;
/**
 * Created by sh on 2016/11/24.
 */

/**
 * 各表数据库常量
 *
 * @author sh
 * @version 1.0 2018/4/11
 * @since 1.0
 */
public interface TableConst {


    /**
     * 航次
     */
    interface Voyage{

        /**
         * 表名
         */
        String TB_VOYAGE = "tb_voyage";

        /**
         * 航次ID
         */
        String SHIP_ID = "ship_id";

        /**
         * 船舶ID
         */
        String V_ID = "v_id";

        /**
         * 泊位号
         */
        String BERTHNO = "berthno";

        /**
         * 航次
         */
        String VOYAGE = "voyage";

        /**
         * 中文船名
         */
        String CHI_VESSEL = "chi_vessel";

        /**
         * 进出口编码
         */
        String CODEINOUT = "codeInOut";

        /**
         * 新航次ID
         */
        String SHIP_NEWID = "ship_newId";

        /**
         * 已选标志
         */
        String SELECTED_MARK = "selected_mark";

        /**
         * 新船舶ID
         */
        String V_NEWID = "v_newid";
    }

    /**
     * 设置
     */
    interface Setting{
        /**
         * 表名
         */
        String TB_SEETING = "tb_setting";

        /**
         * 日期
         */
        String DATE = "date";

        /**
         * 班别编码
         */
        String CODE_CLASS = "code_class";

        /**
         * 节假日标志
         */
        String HOLIDAY_MARK = "holiday_mark";
    }

    /**
     * 贝位规范
     */
    interface BayStandard{

        /**
         * 表名
         */
        String TB_BAY_STADARD = "tb_bay_standard";

        /**
         * 船舶ID
         */
        String V_ID = "v_id";

        /**
         * 英文船名
         */
        String ENG_VESSEL = "eng_vessel";

        /**
         * 中文船名
         */
        String CHI_VESSEL = "chi_vessel";

        /**
         * 甲板/舱内
         */
        String LOCATION = "location";

        /**
         * 屏幕中位置:行
         */
        String SCREEN_ROW = "screen_row";

        /**
         * 屏幕中位置:列
         */
        String SCREEN_COL = "screen_col";

        /**
         * 贝号
         */
        String BAY_NUM = "bay_num";

        /**
         * 贝层
         */
        String BAY_ROW = "bay_row";

        /**
         * 贝列
         */
        String BAY_COL = "bay_col";

        /**
         * 占位标志
         */
        String OCCUPY = "occupy";

        /**
         * 有贝标志
         */
        String USER_CHAR = "user_char";

    }
}
