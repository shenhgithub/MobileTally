package com.lygedi.android.mobiletally.adapter;
/**
 * Created by sh on 2016/11/23.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.bean.Voyage;
import com.lygedi.android.mobiletally.function.VoyageSelectFunction;
import com.lygedi.android.mobiletally.holder.VoyageItemViewHolder;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;


/**
 * 在港航次列表数据适配器
 *
 * @author sh
 * @version 1.0 2016/1/15
 * @since 1.0
 */
public class ArrangeVoyagesRecyclerViewAdapter extends RecyclerView.Adapter<VoyageItemViewHolder> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ArrangeVoyagesRecyclerViewAdapter.";

    /**
     * 数据源
     */
    private List<Voyage> dataList = null;

    /**
     * 记录选中的position
     */
    private SparseBooleanArray selectedItems;

    /**
     * 航次选择数据功能类
     */
    private VoyageSelectFunction voyageSelectFunction = null;

    /**
     * item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>
            onItemClickListener = null;


    /**
     * 构造函数
     */
    public ArrangeVoyagesRecyclerViewAdapter(Context context) {
        this.dataList = new ArrayList<>();
        this.selectedItems = new SparseBooleanArray();
        voyageSelectFunction = new VoyageSelectFunction(context);
    }

    /**
     * 构造函数
     *
     * @param dataList 初始数据源
     */
    public ArrangeVoyagesRecyclerViewAdapter(List<Voyage> dataList) {
        this.dataList = dataList;
    }

    /**
     * 添加一组数据
     *
     * @param position 添加位置
     * @param data     数组
     */
    public void addData(int position, List<Voyage> data) {
        this.dataList.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    /**
     * 重置数据
     *
     * @param data 新数据
     */
    public void reset(List<Voyage> data) {
        this.dataList.clear();
        if (data != null) {
            this.dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {

        Log.i(LOG_TAG + "clear", "is invoked");
        if (dataList.size() > 0) {
            int count = dataList.size();
            dataList.clear();
            selectedItems.clear();
            notifyItemRangeRemoved(0, count);
        }
    }

    /**
     * 切换选择状态
     *
     * @param voyageItemViewHolder 航次列表的ViewHolder
     */
    public void switchSelectedState(VoyageItemViewHolder voyageItemViewHolder) {

        int position = voyageItemViewHolder.getLayoutPosition();

        selectedItems.clear();
        selectedItems.put(position, true);
        notifyDataSetChanged();
    }

    /**
     * 获取选中item数目
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * 获取选中的Item
     *
     * @return Item
     */
    public Integer getSelectedItem() {

        if (selectedItems.size() == 0) {
            return null;
        }

        return selectedItems.keyAt(0);
    }

    /**
     * 获取选中的数据
     *
     * @return 获取选中的数据
     */
    public Voyage getSelectedData() {

        if (selectedItems.size() == 0) {
            return null;
        }

        Voyage selectedData = dataList.get(getSelectedItem());

        return selectedData;
    }

    /**
     * 设置Item点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<Voyage>,
            VoyageItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public VoyageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .voyage_list_item, parent, false);

        // 创建Item布局管理器
        return new VoyageItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VoyageItemViewHolder holder, int position) {

        // 数据绑定
        Voyage voyage = this.dataList.get(position);
        holder.berthnoTextView.setText(voyage.getBerthno());
        holder.inOutTextView.setText(voyage.getCodeInOut().equals("1") == true ? "出" : "进");
        holder.voyageTextView.setText(voyage.getVoyage());
        holder.chi_VesselTextView.setText(voyage.getChi_vessel());


        Voyage voyage_selected = voyageSelectFunction.onLoadSelectedVoyageFromDataBase();
        if (voyage_selected != null) {
            Log.i(LOG_TAG + "onBindViewHolder", "voyage.getShip_id() is " + voyage.getShip_id());
            Log.i(LOG_TAG + "onBindViewHolder", "voyage_selected.getShip_id() is " +
                    voyage_selected.getShip_id());

            //已选择航次
            if (voyage.getShip_id().equals(voyage_selected.getShip_id())) {
                selectedItems.clear();
                selectedItems.put(position, true);
            }
        }


        int color = 0;
        if (selectedItems.get(position, false)) {
            color = Color.parseColor("#00008B");
        } else {
            color = Color.parseColor("#FFFFFF");
        }
        holder.itemView.setBackgroundColor(color);

        // 绑定监听事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(dataList, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }
}
