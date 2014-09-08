package demo.p2p.hl.act;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.base.EsayAdapter;
import demo.p2p.hl.data.Bank;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.view.ItemViewBank_;

@EActivity(R.layout.act_banks)
public class ActBanks extends BaseActivity {
    
    @ViewById
    ListView mListView;

    EsayAdapter<Bank, ItemViewBank_> mListAdapter;
	
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActBanks_.class));
    }
    
    @AfterViews
    void init() {
        mListAdapter = new EsayAdapter<Bank, ItemViewBank_>(this) {};
        mListView.setAdapter(mListAdapter);
        loadData();
        setTitle("银行卡");
    }
    
    @Background
    void loadData() {
        try {
            refresh(Api.getBankList());
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void refresh(List<Bank> list) {
        mListAdapter.setList(list);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
}
