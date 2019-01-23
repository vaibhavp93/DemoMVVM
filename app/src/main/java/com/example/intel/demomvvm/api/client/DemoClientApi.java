package com.example.intel.demomvvm.api.client;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.intel.demomvvm.MainActivity;
import com.example.intel.demomvvm.R;
import com.example.intel.demomvvm.api.ApiService;
import com.example.intel.demomvvm.api.Apis;
import com.example.intel.demomvvm.api.ErrorResponse;
import com.example.intel.demomvvm.helper.ConnectionUtils;
import com.example.intel.demomvvm.helper.PrefUtils;
import com.example.intel.demomvvm.model.ModelDemo;
import com.example.intel.demomvvm.model.RowsBean;
import com.example.intel.demomvvm.viewmodel.DemoViewModel;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoClientApi {

    Context context;
    private ProgressDialog dialog;

    public DemoClientApi(Context context) {
        this.context = context;
    }

    public void getDatas(final DemoCliemtApiCallback demoCliemtApiCallback) {

        if (!ConnectionUtils.isConnected(context)) {
            demoCliemtApiCallback.onError(context.getString(R.string.no_internet));

            // if no internet but data exists in preference
            if (PrefUtils.getFactsResponse(context) != null) {
                demoCliemtApiCallback.onGetFacts(PrefUtils.getFactsResponse(context).getRows());
                demoCliemtApiCallback.onGetTitle(PrefUtils.getFactsResponse(context).getTitle());
            }
            return;
        }
        showDialog();

        ApiService apiService = MainActivity.getRetrofit().create(ApiService.class);
        apiService.getFacts().enqueue(new Callback<ModelDemo>() {
            @Override
            public void onResponse(Call<ModelDemo> call, Response<ModelDemo> response) {
                hideDialog();
                if (response.code() == Apis.OK_RESPONSE) {

                    if (response.body() != null) {

                        // save data to preference for offline mode
                        PrefUtils.setFactsResponse(context, response.body());

                        demoCliemtApiCallback.onGetFacts(response.body().getRows());
                        demoCliemtApiCallback.onGetTitle(response.body().getTitle());
                    } else {
                        demoCliemtApiCallback.onError(context.getString(R.string.cannot_parse));
                    }

                } else {
                    try {
                        ErrorResponse loginError = MainActivity.getGson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        demoCliemtApiCallback.onError(loginError.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        demoCliemtApiCallback.onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDemo> call, Throwable t) {
                hideDialog();
                demoCliemtApiCallback.onError(t.getMessage());
            }
        });
    }

    private void showDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setTitle("Loading..");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    private void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface DemoCliemtApiCallback
    {

        void onError(String error);

        void onGetFacts(ArrayList<RowsBean> facts);

        void onGetTitle(String title);

    }
}
