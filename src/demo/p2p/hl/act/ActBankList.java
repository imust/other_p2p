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
import demo.p2p.hl.data.Bank;
import demo.p2p.hl.event.EventBankSelect;
import demo.p2p.hl.view.ItemViewBank_;

@EActivity(R.layout.act_bank_list)
public class ActBankList extends BaseActivity {

    @ViewById
    ListView mListView;

    EsayAdapter<Bank, ItemViewBank_> mListAdapter;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActBankList_.class));
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
        setTitle("设置银行");
        mListAdapter = new EsayAdapter<Bank, ItemViewBank_>(this) {};
        mListView.setAdapter(mListAdapter);
        mListAdapter.setList(Bank.createDefault());
    }
    
    public void onEventMainThread(EventBankSelect event) {
        finish();
    }
    
    
}
