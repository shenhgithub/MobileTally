package com.lygedi.android.mobiletally.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lygedi.android.mobiletally.bean.Voyage;
import org.mobile.library.model.database.BaseOperator;
import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * 航次数据库操作工具
 *
 * @author sh
 * @version 1.0 2016/11/25
 * @since 1.0
 */
public class VoyageOperator extends BaseOperator<Voyage> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageOperator.";

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VoyageOperator(Context context) {
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
        return TableConst.Voyage.TB_VOYAGE;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY" +
                        " KEY, " +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s INTEGER)",
                tableName, CommonConst._ID,
                TableConst.Voyage.SHIP_ID, TableConst.Voyage.V_ID,
                TableConst.Voyage.BERTHNO, TableConst.Voyage.VOYAGE,
                TableConst.Voyage.CHI_VESSEL, TableConst.Voyage.CODEINOUT,
                TableConst.Voyage.SHIP_NEWID, TableConst.Voyage.SELECTED_MARK,
                TableConst.Voyage.V_NEWID);


        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(Voyage data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();

        cv.put(TableConst.Voyage.SHIP_ID, data.getShip_id());
        cv.put(TableConst.Voyage.V_ID, data.getV_id());
        cv.put(TableConst.Voyage.BERTHNO, data.getBerthno());
        cv.put(TableConst.Voyage.VOYAGE, data.getVoyage());
        cv.put(TableConst.Voyage.CHI_VESSEL, data.getChi_vessel());
        cv.put(TableConst.Voyage.CODEINOUT, data.getCodeInOut());
        cv.put(TableConst.Voyage.SHIP_NEWID, data.getShip_newId());
        cv.put(TableConst.Voyage.SELECTED_MARK, data.getSelected_mark());
        cv.put(TableConst.Voyage.V_NEWID, data.getV_newId());

        return cv;
    }

    @Override
    protected List<Voyage> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int _id = cursor.getColumnIndex(CommonConst._ID);

        int ship_id = cursor.getColumnIndex(TableConst.Voyage.SHIP_ID);
        int v_id = cursor.getColumnIndex(TableConst.Voyage.V_ID);
        int berthno = cursor.getColumnIndex(TableConst.Voyage.BERTHNO);
        int voyage = cursor.getColumnIndex(TableConst.Voyage.VOYAGE);
        int chi_vessel = cursor.getColumnIndex(TableConst.Voyage.CHI_VESSEL);
        int codeinout = cursor.getColumnIndex(TableConst.Voyage.CODEINOUT);
        int ship_newid = cursor.getColumnIndex(TableConst.Voyage.SHIP_NEWID);
        int selected_mark = cursor.getColumnIndex(TableConst.Voyage.SELECTED_MARK);
        int v_newid = cursor.getColumnIndex(TableConst.Voyage.V_NEWID);

        // 数据填充
        List<Voyage> list = new ArrayList<>();

        while (cursor.moveToNext()) {

            Log.i(LOG_TAG + "query", "ship_id is " + cursor.getString(ship_id));
            // 一条记录
            Voyage data = new Voyage();
            data.setId(cursor.getString(_id));
            data.setShip_id(cursor.getString(ship_id));
            data.setV_id(cursor.getString(v_id));
            data.setBerthno(cursor.getString(berthno));
            data.setVoyage(cursor.getString(voyage));
            data.setChi_vessel(cursor.getString(chi_vessel));
            data.setCodeInOut(cursor.getString(codeinout));
            data.setShip_newId(cursor.getString(ship_newid));
            data.setSelected_mark(cursor.getInt(selected_mark));
            data.setV_newId(cursor.getString(v_newid));

            list.add(data);
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    protected String onWhereSql(Voyage data) {
        return String.format("%s='%s'", TableConst.Voyage.SHIP_ID, data.getShip_id());
    }

    @Override
    public List<Voyage> queryWithCondition(String... parameters) {
        Log.i(LOG_TAG + "queryWithCondition", "queryWithCondition is invoked");

        // 查询语句
        String sql = null;

        if (parameters.length == 1) {
            sql = String.format("select * from %s where %s='%s'", tableName, TableConst.Voyage
                    .SHIP_ID, parameters[0]);
        } else {
            //            sql = String.format("select * from %s order by %s desc limit %s,%s",
            // tableName,
            //                    TableConst.ShiftChange.TIME, parameters[0], parameters[1]);
        }
        return query(sql);
    }

    /**
     * 查询已选择航次
     * @return 对象
     */
    public Voyage querySelectedVoyage()
    {
        Log.i(LOG_TAG + "querySelectedVoyage", "querySelectedVoyage is invoked");

        // 查询语句
        String sql = String.format("select * from %s where %s=%s",
                tableName, TableConst.Voyage.SELECTED_MARK, 1);

        Log.i(LOG_TAG + "querySelectedVoyage", "sql is " + sql);

        List<Voyage> voyageList = query(sql);

        Log.i(LOG_TAG + "querySelectedVoyage", "voyageList.size() is " + voyageList.size());
        return voyageList.size() == 0 ? null : voyageList.get(0);
    }
}