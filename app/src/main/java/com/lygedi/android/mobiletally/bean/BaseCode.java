package com.lygedi.android.mobiletally.bean;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 基础代码对象
 *
 * @author 超悟空
 * @version 1.0 2016/7/25
 * @since 1.0
 */
public class BaseCode implements Parcelable {

    /**
     * 显示文本
     */
    @SerializedName("text")
    private String text = null;

    /**
     * 对应代码
     */
    @SerializedName("value")
    private String value = null;

    public BaseCode() {
    }

    protected BaseCode(Parcel in) {
        text = in.readString();
        value = in.readString();
    }

    public static final Creator<BaseCode> CREATOR = new Creator<BaseCode>() {
        @Override
        public BaseCode createFromParcel(Parcel in) {
            return new BaseCode(in);
        }

        @Override
        public BaseCode[] newArray(int size) {
            return new BaseCode[size];
        }
    };

    /**
     * 获取文本
     *
     * @return 显示文本
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本
     *
     * @param text 显示文本
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取代码
     *
     * @return 代码
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置代码
     *
     * @param value 代码
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(value);
    }

    @Override
    public String toString() {
        return text;
    }
}
