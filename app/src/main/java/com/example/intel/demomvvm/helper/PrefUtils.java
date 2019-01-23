package com.example.intel.demomvvm.helper;

import android.content.Context;
import com.example.intel.demomvvm.MainActivity;
import com.example.intel.demomvvm.model.ModelDemo;
import com.example.intel.demomvvm.model.RowsBean;

public class PrefUtils {

    private static String FACTS = "FACTS";

    public static void setFactsResponse(Context context, ModelDemo factsResponse) {
        String toJson = MainActivity.getGson().toJson(factsResponse);
        Prefs.with(context).save(FACTS, toJson);
    }

    public static ModelDemo getFactsResponse(Context context) {
        ModelDemo factsResponse = new ModelDemo();
        String getFactsString = Prefs.with(context).getString(FACTS, "");
        try {
            factsResponse = MainActivity.getGson().fromJson(getFactsString, ModelDemo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factsResponse;
    }
}
