package com.lygedi.android.mobiletally.bean;

/**
 * 航次数据模型
 *
 * @author sh
 * @version 1.0 2018/4/11
 * @since 1.0
 */
public class Voyage {

    /**
     * 唯一编码
     */
    private String id = null;

    /**
     * 航次ID
     */
    private String ship_id = null;

    /**
     * 船舶ID
     */
    private String v_id = null;

    /**
     * 泊位号
     */
    private String berthno = null;

    /**
     * 航次
     */
    private String voyage = null;

    /**
     * 中文船名
     */
    private String chi_vessel = null;

    /**
     * 进出口编码
     */
    private String codeInOut = null;

    /**
     * 新航次ID
     */
    private String ship_newId = null;

    /**
     * 已选标志
     */
    private int selected_mark = 0;

    /**
     * 新船舶ID
     */
    private String v_newId = null;


    /**
     * 获取编码ID
     *
     * @return 编码ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编码ID
     *
     * @param id 编码
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * 获取航次编码
     *
     * @return 航次编码
     */
    public String getShip_id() {
        return ship_id;
    }

    /**
     * 设置航次编码
     *
     * @param ship_id 航次编码
     */
    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    /**
     * 获取船舶编码
     *
     * @return 船舶编码
     */
    public String getV_id() {
        return v_id;
    }

    /**
     * 设置船舶编码
     *
     * @param v_id 船舶编码
     */
    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    /**
     * 获取泊位号
     *
     * @return 泊位号
     */
    public String getBerthno() {
        return berthno;
    }

    /**
     * 设置泊位号
     *
     * @param berthno 泊位号
     */
    public void setBerthno(String berthno) {
        this.berthno = berthno;
    }

    /**
     * 获取航次
     *
     * @return 航次
     */
    public String getVoyage() {
        return voyage;
    }

    /**
     * 设置航次
     *
     * @param voyage 航次
     */
    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    /**
     * 获取中文船名
     *
     * @return 中文船名
     */
    public String getChi_vessel() {
        return chi_vessel;
    }

    /**
     * 设置中文船名
     *
     * @param chi_vessel 中文船名
     */
    public void setChi_vessel(String chi_vessel) {
        this.chi_vessel = chi_vessel;
    }

    /**
     * 获取进出口编码
     *
     * @return 出口编码
     */
    public String getCodeInOut() {
        return codeInOut;
    }

    /**
     * 设置进出口编码
     *
     * @param codeInOut 进出口编码
     */
    public void setCodeInOut(String codeInOut) {
        this.codeInOut = codeInOut;
    }

    /**
     * 获取新航次ID
     *
     * @return 新航次ID
     */
    public String getShip_newId() {
        return ship_newId;
    }

    /**
     * 设置新航次ID
     *
     * @param ship_newId 新航次ID
     */
    public void setShip_newId(String ship_newId) {
        this.ship_newId = ship_newId;
    }

    /**
     * 获取已选标志
     *
     * @return 已选标志
     */
    public int getSelected_mark() {
        return selected_mark;
    }

    /**
     * 设置已选标志
     *
     * @param selected_mark 已选标志
     */
    public void setSelected_mark(int selected_mark) {
        this.selected_mark = selected_mark;
    }

    /**
     * 新船舶ID
     *
     * @return 新船舶ID
     */
    public String getV_newId() {
        return v_newId;
    }

    /**
     * 新船舶ID
     *
     * @param v_newId 新船舶ID
     */
    public void setV_newId(String v_newId) {
        this.v_newId = v_newId;
    }
}