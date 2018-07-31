package com.lygedi.android.mobiletally.work;

import android.util.Log;

import com.lygedi.android.mobiletally.bean.Voyage;
import com.lygedi.android.mobiletally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;
import org.mobile.library.network.communication.Communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取派工航次
 *
 * @author sh
 * @version 1.0 2018/6/6
 * @since 1.0
 */
public class PullArrangeVoyages extends SimpleWorkModel<String, List<Voyage>> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullArrangeVoyages.";

    /**
     * 航次列表
     */
    private List<Voyage> voyageList = null;

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Work_No", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " Work_No is " + parameters[0]);
        dataMap.put("Work_Date", parameters[1]);
        Log.i(LOG_TAG + " onFillRequestParameters", " Work_Date is " + parameters[1]);
        dataMap.put("Night_Mark", parameters[2]);
        Log.i(LOG_TAG + " onFillRequestParameters", "Night_Mark is " + parameters[2]);
    }

    @Override
    protected List<Voyage> onSuccessExtract(JSONObject jsonResult) throws Exception {
        // 数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get voyageList count is " + jsonArray.length());

        // 新建航次列表
        voyageList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONArray jsonRow = jsonArray.getJSONArray(i);
            Log.i(LOG_TAG + "onSuccessExtract", " voyage jsonRow length() is " + jsonRow.length());

            if (jsonRow.length() > 7) {
                // 一条航次数据
                Voyage voyage = new Voyage();

                voyage.setShip_newId(jsonRow.getString(0));
                voyage.setShip_id(jsonRow.getString(1));
                voyage.setV_id(jsonRow.getString(2));
                voyage.setBerthno(jsonRow.getString(3));
                voyage.setVoyage(jsonRow.getString(4));
                voyage.setChi_vessel(jsonRow.getString(5));
                voyage.setCodeInOut(jsonRow.getString(6));
                voyage.setV_newId(jsonRow.getString(7));


                // 添加到列表
                voyageList.add(voyage);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " voyage list count is " + voyageList.size());

        return voyageList;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.HTTP_GET_ARRANGE_VOYAGES_URL;
    }

    @Override
    protected Communication onCreateCommunication() {
        return super.onCreateCommunication();
    }
}


