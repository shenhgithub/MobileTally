package com.lygedi.android.mobiletally.bean;

/**
 * 贝位规范数据模型
 *
 * @author sh
 * @version 1.0 2018/6/22
 * @since 1.0
 */

public class BayStandard {

    /**
     * 唯一编码
     */
    private String id = null;

    /**
     * 船舶ID
     */
    private int v_id = 0;

    /**
     * 英文船名
     */
    private String eng_vessel = null;

    /**
     * 中文船名
     */
    private String chi_vessel = null;

    /**
     * 甲板/舱内
     */
    private String location = null;

    /**
     * 屏幕中位置:行
     */
    private int screen_row = 0;

    /**
     * 屏幕中位置:列
     */
    private int screen_col = 0;

    /**
     * 贝号
     */
    private String bay_num = null;

    /**
     * 贝层
     */
    private String bay_row = null;

    /**
     * 贝列
     */
    private String bay_col = null;

    /**
     * 占位标志
     */
    private int occupy = 0;

    /**
     * 有贝标志
     */
    private String user_char = null;


    /**
     * 获取唯一编码
     * @return 唯一编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编码
     * @param id 唯一编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取船舶ID
     * @return 船舶ID
     */
    public int getV_id() {
        return v_id;
    }

    /**
     * 设置船舶ID
     * @param v_id 船舶ID
     */
    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    /**
     * 获取英文船名
     * @return 英文船名
     */
    public String getEng_vessel() {
        return eng_vessel;
    }

    /**
     * 设置英文船名
     * @param eng_vessel 英文船名
     */
    public void setEng_vessel(String eng_vessel) {
        this.eng_vessel = eng_vessel;
    }

    /**
     * 获取中文船名
     * @return 中文船名
     */
    public String getChi_vessel() {
        return chi_vessel;
    }

    /**
     * 设置中文船名
     * @param chi_vessel 中文船名
     */
    public void setChi_vessel(String chi_vessel) {
        this.chi_vessel = chi_vessel;
    }

    /**
     * 获取甲板/舱内
     * @return 甲板/舱内
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置甲板/舱内
     * @param location 甲板/舱内
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取屏幕中位置:行
     * @return 屏幕中位置:行
     */
    public int getScreen_row() {
        return screen_row;
    }

    /**
     * 设置屏幕中位置:行
     * @param screen_row 屏幕中位置:行
     */
    public void setScreen_row(int screen_row) {
        this.screen_row = screen_row;
    }

    /**
     * 获取屏幕中位置:列
     * @return 屏幕中位置:列
     */
    public int getScreen_col() {
        return screen_col;
    }

    /**
     * 设置屏幕中位置:列
     * @param screen_col 屏幕中位置:列
     */
    public void setScreen_col(int screen_col) {
        this.screen_col = screen_col;
    }

    /**
     * 获取贝号
     * @return 贝号
     */
    public String getBay_num() {
        return bay_num;
    }

    /**
     * 设置贝号
     * @param bay_num 贝号
     */
    public void setBay_num(String bay_num) {
        this.bay_num = bay_num;
    }

    /**
     * 获取贝层
     * @return 贝层
     */
    public String getBay_row() {
        return bay_row;
    }

    /**
     * 设置贝层
     * @param bay_row 贝层
     */
    public void setBay_row(String bay_row) {
        this.bay_row = bay_row;
    }

    /**
     * 获取贝列
     * @return 贝列
     */
    public String getBay_col() {
        return bay_col;
    }

    /**
     * 设置英贝列
     * @param bay_col 贝列
     */
    public void setBay_col(String bay_col) {
        this.bay_col = bay_col;
    }

    /**
     * 获取占位标志
     * @return 占位标志
     */
    public int getOccupy() {
        return occupy;
    }

    /**
     * 设置占位标志
     * @param occupy 占位标志
     */
    public void setOccupy(int occupy) {
        this.occupy = occupy;
    }

    /**
     * 获取有贝标志
     * @return 有贝标志
     */
    public String getUser_char() {
        return user_char;
    }

    /**
     * 设置有贝标志
     * @param user_char 有贝标志
     */
    public void setUser_char(String user_char) {
        this.user_char = user_char;
    }

}
