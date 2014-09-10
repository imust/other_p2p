package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
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
import demo.p2p.hl.view.ItemViewProvince_;

@EActivity(R.layout.act_province_list)
public class ActProvinceList extends BaseActivity {

    @ViewById
    ListView mListView;

    private Province mSelectProvince;
    
    EsayAdapter<Province, ItemViewProvince_> mListAdapter;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActProvinceList_.class));
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
        setTitle("选择省份");
        mListAdapter = new EsayAdapter<Province, ItemViewProvince_>(this) {};
        mListView.setAdapter(mListAdapter);
        mListAdapter.setList(Province.createDefault());
    }
    
    public void onEventMainThread(Province province) {
        mSelectProvince = province;
        if (mSelectProvince.selectCity == null) {
            ActCityList.start(this, province);
        }
    }
    
    public void onEventMainThread(City city) {
        mSelectProvince.selectCity = city;
        postSticky(mSelectProvince);
        finish();
    }
    
}
