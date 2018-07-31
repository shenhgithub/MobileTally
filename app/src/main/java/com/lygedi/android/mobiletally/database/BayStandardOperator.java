package com.lygedi.android.mobiletally.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lygedi.android.mobiletally.bean.BayStandard;

import org.mobile.library.model.database.BaseOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝位规范数据库操作工具
 *
 * @author sh
 * @version 1.0 2018/6/22
 * @since 1.0
 */
public class BayStandardOperator extends BaseOperator<BayStandard> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayStandardOperator.";

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public BayStandardOperator(Context context) {
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
        return TableConst.BayStandard.TB_BAY_STADARD;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY" +
                        " KEY, " +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s TEXT)",
                tableName, CommonConst._ID,
                TableConst.BayStandard.V_ID, TableConst.BayStandard.ENG_VESSEL,
                TableConst.BayStandard.CHI_VESSEL, TableConst.BayStandard.LOCATION,
                TableConst.BayStandard.SCREEN_ROW, TableConst.BayStandard.SCREEN_COL,
                TableConst.BayStandard.BAY_NUM, TableConst.BayStandard.BAY_ROW,
                TableConst.BayStandard.BAY_COL,TableConst.BayStandard.OCCUPY,
                TableConst.BayStandard.USER_CHAR);



        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(BayStandard data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();

        cv.put(TableConst.BayStandard.V_ID, data.getV_id());
        cv.put(TableConst.BayStandard.ENG_VESSEL, data.getEng_vessel());
        cv.put(TableConst.BayStandard.CHI_VESSEL, data.getChi_vessel());
        cv.put(TableConst.BayStandard.LOCATION, data.getLocation());
        cv.put(TableConst.BayStandard.SCREEN_ROW, data.getScreen_row());
        cv.put(TableConst.BayStandard.SCREEN_COL, data.getScreen_col());
        cv.put(TableConst.BayStandard.BAY_NUM, data.getBay_num());
        cv.put(TableConst.BayStandard.BAY_ROW, data.getBay_row());
        cv.put(TableConst.BayStandard.BAY_COL, data.getBay_col());
        cv.put(TableConst.BayStandard.OCCUPY, data.getOccupy());
        cv.put(TableConst.BayStandard.USER_CHAR, data.getUser_char());

        return cv;
    }

    @Override
    protected List<BayStandard> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int _id = cursor.getColumnIndex(CommonConst._ID);

        int v_id = cursor.getColumnIndex(TableConst.BayStandard.V_ID);
        int eng_vessel = cursor.getColumnIndex(TableConst.BayStandard.ENG_VESSEL);
        int chi_vessel = cursor.getColumnIndex(TableConst.BayStandard.CHI_VESSEL);
        int location = cursor.getColumnIndex(TableConst.BayStandard.LOCATION);
        int screen_row = cursor.getColumnIndex(TableConst.BayStandard.SCREEN_ROW);
        int screen_col = cursor.getColumnIndex(TableConst.BayStandard.SCREEN_COL);
        int bay_num = cursor.getColumnIndex(TableConst.BayStandard.BAY_NUM);
        int bay_row = cursor.getColumnIndex(TableConst.BayStandard.BAY_ROW);
        int bay_col = cursor.getColumnIndex(TableConst.BayStandard.BAY_COL);
        int occupy = cursor.getColumnIndex(TableConst.BayStandard.OCCUPY);
        int user_char = cursor.getColumnIndex(TableConst.BayStandard.USER_CHAR);

        // 数据填充
        List<BayStandard> list = new ArrayList<>();

        while (cursor.moveToNext()) {

            Log.i(LOG_TAG + "query", "v_id is " + cursor.getString(v_id));
            // 一条记录
            BayStandard data = new BayStandard();
            data.setId(cursor.getString(_id));
            data.setV_id(cursor.getInt(v_id));
            data.setEng_vessel(cursor.getString(eng_vessel));
            data.setChi_vessel(cursor.getString(chi_vessel));
            data.setLocation(cursor.getString(location));
            data.setScreen_row(cursor.getInt(screen_row));
            data.setScreen_col(cursor.getInt(screen_col));
            data.setBay_num(cursor.getString(bay_num));
            data.setBay_row(cursor.getString(bay_row));
            data.setBay_col(cursor.getString(bay_col));
            data.setOccupy(cursor.getInt(occupy));
            data.setUser_char(cursor.getString(user_char));

            list.add(data);
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    protected String onWhereSql(BayStandard data) {
        return String.format("%s='%s'", TableConst.BayStandard.V_ID, data.getV_id());
    }

    @Override
    public List<BayStandard> queryWithCondition(String... parameters) {
        Log.i(LOG_TAG + "queryWithCondition", "queryWithCondition is invoked");

        return null;
    }

    /**
     * 删除数据（船舶贝位规范）
     *
     * @param data 贝位规范对象
     *
     * @return 删除的记录数
     */
    public int deleteBayStandard(BayStandard data) {
        Log.v(LOG_TAG + "delete", "delete is invoked");

        if (data == null) {
            Log.v(LOG_TAG + "delete", "data is null");
            return 0;
        }

        // 得到数据库写对象
        SQLiteDatabase dbWriter = writeSqLiteHelper.getWritableDatabase();


        // 执行删除
        int rowCount = dbWriter.delete(tableName, onWhereSql(data), null);

        Log.v(LOG_TAG + "delete", "delete row count is " + rowCount);

        close(writeSqLiteHelper);

        return rowCount;
    }

    /**
     *  根据船舶编码判断贝位规范是否存在
     *
     * @param v_id 船舶编码
     *
     * @return 数据对象，没有返回null
     */
    public boolean isExistByV_id(String v_id) {
        Log.i(LOG_TAG + "isExistByV_id", "isExist v_id is " + v_id);

        // 查询语句
        String sql = String.format("select count(*) from %s where %s=%s", tableName,  TableConst
                .BayStandard.V_ID, v_id);

        Log.i(LOG_TAG + "isExistByV_id", "query sql is " + sql);

        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToNext()) {
            Log.i(LOG_TAG + "isExistByV_id", "count is " + cursor.getInt(0));
            if (cursor.getInt(0) > 0) {
                cursor.close();
                close(sqLiteHelper);
                Log.i(LOG_TAG + "isExistByV_id", "isExist v_id is " + v_id + " true");
                return true;
            }
        }
        cursor.close();
        close(sqLiteHelper);

        Log.i(LOG_TAG + "isExistByV_id", "isExist v_id is " + v_id + " false");
        return false;
    }



}