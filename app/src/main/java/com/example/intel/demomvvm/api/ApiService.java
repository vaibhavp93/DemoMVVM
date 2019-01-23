package com.example.intel.demomvvm.api;

import com.example.intel.demomvvm.model.ModelDemo;
import com.example.intel.demomvvm.viewmodel.DemoViewModel;

import retrofit2.Call;
import retrofit2.http.GET;

// interface for retrofit
public interface ApiService {

    @GET(Apis.FACTS)
    Call<ModelDemo> getFacts();

}
