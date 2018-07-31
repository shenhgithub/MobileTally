package com.lygedi.android.mobiletally.work;

import android.util.Log;

import com.lygedi.android.mobiletally.bean.BayStandard;
import com.lygedi.android.mobiletally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;
import org.mobile.library.network.factory.NetworkType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下载贝位规范
 *
 * @author sh
 * @version 1.0 2018/6/25
 * @since 1.0
 */

public class DownloadBayStandard extends SimpleWorkModel<String, List<BayStandard>> {

    private static final String LOG_TAG="DownloadBayStandard.";

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("V_Id", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " V_Id is " + parameters[0]);
    }

    @Override
    protected List<BayStandard> onSuccessExtract(JSONObject jsonResult) throws Exception {

        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + " onSuccessExtract", " get voyageList count is " + jsonArray.length());

        //新建贝位规范列表
        List<BayStandard> bayStandardList =  new ArrayList<>();
        for(int i=0; i<jsonArray.length();i++){
            JSONArray jsonRow = jsonArray.getJSONArray(i);

            if(jsonRow.length() > 10){
                BayStandard bayStandard = new BayStandard();
                bayStandard.setV_id(jsonRow.getInt(0));
                bayStandard.setEng_vessel(jsonRow.getString(1));
                bayStandard.setLocation(jsonRow.getString(2));
                bayStandard.setBay_num(jsonRow.getString(3));
                bayStandard.setScreen_row(jsonRow.getInt(4));
                bayStandard.setScreen_col(jsonRow.getInt(5));
                bayStandard.setBay_num(jsonRow.getString(6));
                bayStandard.setBay_row(jsonRow.getString(7));
                bayStandard.setBay_col(jsonRow.getString(8));
                bayStandard.setOccupy(jsonRow.getInt(9));
                bayStandard.setUser_char(jsonRow.getString(10));

                bayStandardList.add(bayStandard);
            }
        }


        Log.i(LOG_TAG + "onSuccessExtract", "bayStandardList count is " + bayStandardList.size());


        return bayStandardList;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.HTTP_DOWNLOAD_BAY_STANARD_URL;
    }

//    @Override
//    protected NetworkType onNetworkType() {
//        return NetworkType.DOWNLOAD;
//    }
}
