package com.lygedi.android.mobiletally.bean;

/**
 * 设置数据模型
 *
 * @author sh
 * @version 1.0 2018/4/11
 * @since 1.0
 */
public class Setting {

    /**
     * 日期
     */
    private String date = null;

    /**
     * 班别编码
     */
    private String code_class = "01";

    /**
     * 节假日标志
     */
    private int holiday_mark = 0;


    public Setting() {

    }

    public Setting(String date, String code_class, int holiday_mark) {
        this.date = date;
        this.code_class = code_class;
        this.holiday_mark = holiday_mark;
    }


    /**
     * 获取日期
     *
     * @return 日期
     */
    public String getDate() {
        return date;
    }
    /**
     * 设置日期
     *
     * @param date 日期
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取班别
     *
     * @return 班别
     */
    public String getCode_class() {
        return code_class;
    }
    /**
     * 设置班别
     *
     * @param code_class 班别
     */
    public void setCode_class(String code_class) {
        this.code_class = code_class;
    }

    /**
     * 获取节假日标志
     *
     * @return 节假日标志
     */
    public int getHoliday_mark() {
        return holiday_mark;
    }
    /**
     * 设置节假日标志
     *
     * @param holiday_mark 节假日标志
     */
    public void setHoliday_mark(int holiday_mark) {
        this.holiday_mark = holiday_mark;
    }

}