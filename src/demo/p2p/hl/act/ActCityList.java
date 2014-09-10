package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.base.EsayAdapter;
import demo.p2p.hl.data.City;
import demo.p2p.hl.data.Province;
import demo.p2p.hl.view.ItemViewCity_;

@EActivity(R.layout.act_city_list)
public class ActCityList extends BaseActivity {

    @ViewById
    ListView mListView;
    
    @Extra("province")
    Province mProvince;

    EsayAdapter<City, ItemViewCity_> mListAdapter;
    
    public static void start(Context context, Province province) {
        Intent intent = new Intent(context, ActCityList_.class);
        intent.putExtra("province", province);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }
    
    @AfterViews
    void init() {
        setTitle("选择城市");
        mListAdapter = new EsayAdapter<City, ItemViewCity_>(this) {};
        mListView.setAdapter(mListAdapter);
        mListAdapter.setList(mProvince.cities);
    }
    
    public void onEventMainThread(City city) {
        finish();
    }
    
}
