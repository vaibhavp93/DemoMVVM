package com.example.intel.demomvvm.viewmodel;

import android.content.Context;

import com.example.intel.demomvvm.api.client.DemoClientApi;
import com.example.intel.demomvvm.model.ModelDemo;
import com.example.intel.demomvvm.model.RowsBean;

import java.util.ArrayList;

public class DemoViewModel {

    Context context;
    ArrayList<ModelDemo> modelDemos;
    DemoCallBack demoCallBack;

    public DemoViewModel(Context context,DemoCallBack demoCallBack) {
        this.context = context;
        this.demoCallBack = demoCallBack;
    }

    public void getDatas()
    {

        DemoClientApi client = new DemoClientApi(context);
        client.getDatas(new DemoClientApi.DemoCliemtApiCallback() {


            @Override
            public void onError(String error) {
                demoCallBack.onError(error);
            }

            @Override
            public void onGetFacts(ArrayList<RowsBean> facts) {
                demoCallBack.onGetDatas(facts);
            }

            @Override
            public void onGetTitle(String title) {
                demoCallBack.onGetTitle(title);
            }
        });

    }

    public interface DemoCallBack {

        void onError(String error);

        void onGetDatas(ArrayList<RowsBean> modelDemos);

        void onGetTitle(String title);

    }
}
