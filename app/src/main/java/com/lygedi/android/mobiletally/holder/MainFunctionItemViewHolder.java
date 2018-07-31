package com.lygedi.android.mobiletally.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lygedi.android.mobiletally.R;

/**
 * 主界面功能列表的ViewHolder
 *
 * @author sh
 * @version 1.0 2016/11/8
 * @since 1.0
 */
public class MainFunctionItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 功能图标
     */
    public ImageView iconImageView = null;

    /**
     * 功能标题
     */
    public TextView titleTextView = null;

    /**
     * 文本1
     */
    public TextView textView1 = null;
    /**
     * 文本2
     */
    public TextView textView2 = null;
    /**
     * 文本3
     */
    public TextView textView3 = null;

    public MainFunctionItemViewHolder(View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.function_grid_item_image);

        titleTextView = (TextView) itemView.findViewById(R.id.function_grid_item_text);

        textView1 = (TextView) itemView.findViewById(R.id.function_grid_item_text1);

        textView2 = (TextView) itemView.findViewById(R.id.function_grid_item_text2);

        textView3 = (TextView) itemView.findViewById(R.id.function_grid_item_text3);
    }
}
