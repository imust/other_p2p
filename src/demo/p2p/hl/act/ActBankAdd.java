package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.event.EventBankSelect;

@EActivity(R.layout.act_bank_add)
public class ActBankAdd extends BaseActivity {
    
    @ViewById
    EditText mCard;
    @ViewById
    EditText mInitBank;
    @ViewById
    EditText mCode;
    @ViewById
    TextView mBank;
    @ViewById
    TextView mCity;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActBankAdd_.class));
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
        setTitle("新增银行卡");
    }
    
    @Click(R.id.mBankContainer)
    void onBankClick() {
        ActBankList_.start(this);
    }
    
    @Click(R.id.mCityContainer)
    void onCityClick() {
    }
    
    public void onEventMainThread(EventBankSelect event) {
        mBank.setText(event.bank.name);
    }
    
    
    
}
