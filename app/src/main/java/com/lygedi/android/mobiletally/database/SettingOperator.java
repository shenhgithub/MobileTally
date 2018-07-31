package com.lygedi.android.mobiletally.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lygedi.android.mobiletally.bean.Setting;
import com.lygedi.android.mobiletally.bean.Voyage;

import org.mobile.library.model.database.BaseOperator;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.lygedi.android.mobiletally.R.mipmap.voyage;

/**
 * 设置数据库操作工具
 *
 * @author sh
 * @version 1.0 2016/11/25
 * @since 1.0
 */
public class SettingOperator extends BaseOperator<Setting> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SettingOperator.";

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public SettingOperator(Context context) {
        super(context);
        onCreateTableName();
    }

    @Override
    protected SQLiteOpenHelper onCreateDatabaseHelper(Context context) {
        return new DBSQLiteHelper(context);
    }

    @Override
    protected SQLiteOpenHelper onCreateWriteDatabaseHelper(Context context) {
        return DBSQLiteHelper.getSqLiteOpenHelper(context);
    }

    @Override
    protected String onCreateTableName() {
        return TableConst.Setting.TB_SEETING;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s INTEGER)",
                tableName,
                TableConst.Setting.DATE, TableConst.Setting.CODE_CLASS,
                TableConst.Setting.HOLIDAY_MARK);


        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(Setting data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();

        cv.put(TableConst.Setting.DATE, data.getDate());
        cv.put(TableConst.Setting.CODE_CLASS, data.getCode_class());
        cv.put(TableConst.Setting.HOLIDAY_MARK, data.getHoliday_mark());

        return cv;
    }

    @Override
    protected List<Setting> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        int date = cursor.getColumnIndex(TableConst.Setting.DATE);
        int code_class = cursor.getColumnIndex(TableConst.Setting.CODE_CLASS);
        int holiday_mark = cursor.getColumnIndex(TableConst.Setting.HOLIDAY_MARK);

        // 数据填充
        List<Setting> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录
            Setting data = new Setting();
            data.setDate(cursor.getString(date));
            data.setCode_class(cursor.getString(code_class));
            data.setHoliday_mark(cursor.getInt(holiday_mark));

            list.add(data);
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    protected String onWhereSql(Setting data) {
        return null;
    }

    @Override
    public List<Setting> queryWithCondition(String... parameters) {
        Log.i(LOG_TAG + "queryWithCondition", "queryWithCondition is invoked");

        return null;
    }

    /**
     * 查询设置数据
     * @return 对象
     */
    public Setting querySetting()
    {
        Log.i(LOG_TAG + "querySetting", "querySetting is invoked");

        // 查询语句
        String sql = String.format("select * from %s",
                tableName);

        Log.i(LOG_TAG + "querySetting", "sql is " + sql);

        List<Setting> settingList = query(sql);

        Log.i(LOG_TAG + "querySetting", "settingList.size() is " + settingList.size());

        return settingList.size() == 0 ? null : settingList.get(0);
    }

}