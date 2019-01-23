package com.example.intel.demomvvm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.intel.demomvvm.R;
import com.example.intel.demomvvm.adapter.DemoAdapter;
import com.example.intel.demomvvm.helper.LineDividerItemDecoration;
import com.example.intel.demomvvm.model.RowsBean;
import com.example.intel.demomvvm.viewmodel.DemoViewModel;

import java.util.ArrayList;

public class SampleActivity extends AppCompatActivity implements DemoViewModel.DemoCallBack {

    private android.support.v7.widget.RecyclerView recycleView;
    private DemoAdapter adapter;
    DemoViewModel demoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        this.recycleView = (RecyclerView) findViewById(R.id.recycleView);

        recycleView.addItemDecoration(new LineDividerItemDecoration(SampleActivity.this, R.drawable.line_divider));
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DemoAdapter(SampleActivity.this);
        recycleView.setAdapter(adapter);

        demoViewModel = new DemoViewModel(SampleActivity.this,this);
        demoViewModel.getDatas();

    }

    @Override
    public void onError(String error) {
        Toast.makeText(SampleActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDatas(ArrayList<RowsBean> modelDemos) {
        adapter.setDatas(modelDemos);
    }


    @Override
    public void onGetTitle(String title) {

    }
}
