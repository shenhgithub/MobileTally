package com.lygedi.android.mobiletally.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lygedi.android.mobiletally.R;
import com.lygedi.android.mobiletally.bean.Setting;
import com.lygedi.android.mobiletally.function.SettingFunction;

import org.mobile.library.common.function.ToolbarInitialize;

import java.util.Calendar;


/**
 * 设置Activity
 *
 * @author sh
 * @version 1.0 2018/4/12
 * @since 1.0
 */
public class SettingActivity extends AppCompatActivity {


    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SettingActivity.";

    /**
     * 日期
     */
    private TextView dateText = null;

    /**
     * 班别
     */
    private Spinner classSpinner = null;

    /**
     * 节假日地址开关
     */
    private ToggleButton holidaytToggleButton = null;

    /**
     * 设置数据功能类
     */
    private SettingFunction settingFunction = null;

    private Setting setting = null;

    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        //初始化控件引用
        initViewHolder();
        //加载界面
        initView();
        //保存设置
        SaveSetting();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        dateText = (TextView) findViewById(R.id.activity_setting_date_text);
        holidaytToggleButton = (ToggleButton) findViewById(R.id
                .activity_setting_holiday_toggleButton);
        classSpinner = (Spinner) findViewById(R.id.activity_setting_class_spinner);

        settingFunction = new SettingFunction(this);
        setting = settingFunction.onLoadSettingFromDataBase();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.setting, true, true);
        //初始化时间控件
        initDateTime();
        //初始化班别控件
        initClass();
        //初始化节假日开关
        initHolidayToggle();
    }

    /**
     * 初始化时间控件
     */
    private void initDateTime() {

        //获取当前日期
        Calendar ca = Calendar.getInstance();
        int year = 0;
        int month = 0;
        int day = 0;

        if (setting != null) {
            //获取本地日期
            String setting_date = setting.getDate();
            String[] dateList = setting_date.split("\\-");
            String strYear = dateList[0];
            String strMonth = dateList[1];
            String strDay = dateList[2];
            Log.i(LOG_TAG + "initDateTime", "setting_date is " + setting_date);
            Log.i(LOG_TAG + "initDateTime", "strYear is " + strYear);
            Log.i(LOG_TAG + "initDateTime", "strMonth is " + strMonth);
            Log.i(LOG_TAG + "initDateTime", "strDay is " + strDay);

            year = Integer.parseInt(strYear);
            month = Integer.parseInt(strMonth);
            day = Integer.parseInt(strDay);
        }

        String date;
        date = new StringBuffer().append(year).append("-").append(month).append("-").append(day)
                .toString();
        dateText.setText(date);
        Log.i(LOG_TAG + "initDateTime", "date is " + date);

        mYear = year;
        mMonth = month;
        mDay = day;
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 调用时间选择器

                new DatePickerDialog(SettingActivity.this, onDateSetListener, mYear,
                        mMonth-1, mDay).show();
            }
        });
    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog
            .OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            String date;
            date = new StringBuffer().append(year).append("-").append(month + 1).append("-")
                    .append(day).toString();
            dateText.setText(date);
            mYear = year;
            mMonth = month + 1;
            mDay = day;
            SaveSetting();

            Toast.makeText(getApplication(), R.string.date_change_success, Toast.LENGTH_SHORT)
                    .show();
        }
    };

    /**
     * 初始化班别控件
     */
    private void initClass() {

        String[] classList = getResources().getStringArray(R.array.class_value);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, classList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);

        if (setting != null) {
            //从本地获取班别
            classSpinner.setSelection(setting.getCode_class().equals("1") == true ? 0 : 1, true);
        }

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SaveSetting();
                Toast.makeText(getApplication(), R.string.class_change_success, Toast
                        .LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    /**
     * 初始化节假日开关
     */
    private void initHolidayToggle() {

        if (setting != null) {
            //从本地获取节假日开关
            boolean h = setting.getHoliday_mark() == 0 ? false : true;
            holidaytToggleButton.setChecked(h);
        }

        holidaytToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSetting();
                Toast.makeText(getApplication(), R.string.holiday_change_success, Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 保存设置
     */
    private void SaveSetting() {

        String date = dateText.getText().toString();
        String code_class = classSpinner.getSelectedItemPosition() == 0 ? "01" : "02";
        int holiday_mark = holidaytToggleButton.isChecked() == true ? 1 : 0;

        Log.i(LOG_TAG + "SaveSetting", "strDate is " + date);
        Log.i(LOG_TAG + "SaveSetting", "code_class is " + code_class);
        Log.i(LOG_TAG + "SaveSetting", "holiday_mark is " + holiday_mark);


        settingFunction.onSaveSetting(new Setting(date, code_class, holiday_mark));
    }
}

