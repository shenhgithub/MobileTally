package com.lygedi.android.mobiletally.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.bean.Setting;
import com.lygedi.android.mobiletally.bean.Voyage;
import com.lygedi.android.mobiletally.function.SettingFunction;
import com.lygedi.android.mobiletally.function.VoyageSelectFunction;
import com.lygedi.android.mobiletally.holder.MainFunctionItemViewHolder;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L;

/**
 * 主界面功能列表数据适配器
 *
 * @author sh
 * @version 1.0 2018/4/9
 * @since 1.0
 */
public class MainFunctionRecyclerViewAdapter extends RecyclerView
        .Adapter<MainFunctionItemViewHolder> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MainFunctionRecyclerViewAdapter.";

    /**
     * 功能名称数组
     */
    private String[] functionTitle = null;

    /**
     * 资源类型数组
     */
    private TypedArray images = null;

    /**
     * 航次选择数据功能类
     */
    private VoyageSelectFunction voyageSelectFunction = null;

    /**
     * 设置数据功能类
     */
    private SettingFunction settingFunction = null;

    /**
     * item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<String[], MainFunctionItemViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public MainFunctionRecyclerViewAdapter(Context context) {
        // 加载标题
        functionTitle = context.getResources().getStringArray(R.array.grid_item_function_name_list);
        // 加载图标
        images = context.getResources().obtainTypedArray(R.array.grid_item_function_image_list);

        voyageSelectFunction = new VoyageSelectFunction(context);
        settingFunction = new SettingFunction(context);
    }

    /**
     * 设置Item点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<String[],
            MainFunctionItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MainFunctionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .function_grid_item, parent, false);

        // 创建Item布局管理器
        return new MainFunctionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainFunctionItemViewHolder holder, int position) {

        // 数据绑定
        holder.titleTextView.setText(functionTitle[position]);
        holder.iconImageView.setImageResource(images.getResourceId(position, R.mipmap.ic_launcher));

        Voyage voyage = voyageSelectFunction.onLoadSelectedVoyageFromDataBase();
        Setting setting = settingFunction.onLoadSettingFromDataBase();

        if (position == 1 && voyage != null) {
            holder.textView1.setText(voyage.getChi_vessel());
            holder.textView2.setText(voyage.getVoyage());
            holder.textView3.setText(voyage.getCodeInOut().equals("1") ? "出口" : "进口");
        }
        if (position == 0 && setting != null) {
            holder.textView1.setText(setting.getDate());
            holder.textView2.setText(setting.getCode_class().equals("1") ? "白班" : "夜班");
            holder.textView3.setText(setting.getHoliday_mark() == 0 ? "" : "节");
        } else if (position == 0 && setting == null) {
            //设置日期
            Calendar ca = Calendar.getInstance();
            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH) + 1;
            int day = ca.get(Calendar.DAY_OF_MONTH);
            String date = new StringBuffer().append(year).append("-").append(month).append("-")
                    .append(day).toString();
            holder.textView1.setText(date);

            //设置白夜班
            String code_class = "01";

            Calendar ca_start = Calendar.getInstance();
            ca_start.set(Calendar.HOUR_OF_DAY, 8);
            ca_start.set(Calendar.MINUTE, 0);
            ca_start.set(Calendar.SECOND, 0);
            ca_start.set(Calendar.MILLISECOND, 0);

            Calendar ca_end = Calendar.getInstance();
            ca_end.set(Calendar.HOUR_OF_DAY, 18);
            ca_end.set(Calendar.MINUTE, 0);
            ca_end.set(Calendar.SECOND, 0);
            ca_end.set(Calendar.MILLISECOND, 0);

            Log.i(LOG_TAG + "onBindViewHolder", "ca is " + ca.getTime());
            Log.i(LOG_TAG + "onBindViewHolder", "ca_start is " + ca_start.getTime());
            Log.i(LOG_TAG + "onBindViewHolder", "ca_end is " + ca_end.getTime());
            Log.i(LOG_TAG + "onBindViewHolder", "ca.after(ca_start) is " + ca.after(ca_start));
            Log.i(LOG_TAG + "onBindViewHolder", "ca.before(ca_end) is " + ca.before(ca_end));
            if (ca.after(ca_start) && ca.before(ca_end)) {
                holder.textView2.setText("白班");
            } else {
                holder.textView2.setText("夜班");
                code_class = "02";
            }

            //设置节假日
            int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK) - 1;
            int holiday_mark = 0;
            if ((dayOfWeek == 5 && ca.after(ca_end)) || dayOfWeek == 6 || (dayOfWeek == 7 && ca
                    .before(ca_end))) {
                holiday_mark = 1;
            }

            settingFunction.onSaveSetting(new Setting(date, code_class, holiday_mark));
        }


        // 绑定监听事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(functionTitle, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.functionTitle.length;
    }
}
