package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.Bank;
import demo.p2p.hl.data.BankCard;
import demo.p2p.hl.data.Province;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

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
    @ViewById
    Button mSendCodeButton;
    
    private String mSelectBankName;
    private String mSelectProvince;
    private String mSelectCity;
    
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
        ActBankList.start(this);
    }
    
    @Click(R.id.mCityContainer)
    void onCityClick() {
        ActProvinceList.start(this);
    }
    
    @Click(R.id.mSendCodeButton)
    void onSendCodeClick() {
        sendCode();
    }
    
    @Background
    void sendCode() {
        try {
            Api.sendPayCode();
            onSendCodeSuccess();
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onSendCodeSuccess() {
        ToastUtil.getDefault().show("验证码已发送到手机:********" + UserSession.get().getUser().phone);
        mSendCodeButton.setEnabled(false);
    }
    
    public void onEventMainThread(Bank bank) {
        mSelectBankName = bank.name;
        mBank.setText(bank.name);
    }
    
    public void onEventMainThread(Province province) {
        if (province != null && province.selectCity != null) {
            mSelectProvince = province.name;
            mSelectCity = province.selectCity.name;
            mCity.setText(province.name + " " + province.selectCity.name);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_card_add, menu);
        return super.onCreateOptionsMenu(menu);
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.add:
            onCommit();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    
    void onCommit() {
        
        String code = mCode.getText().toString();
        
        BankCard bankCard = new BankCard();
        bankCard.card = mCard.getText().toString();
        bankCard.province = mSelectProvince;
        bankCard.city = mSelectCity;
        bankCard.bankName = mSelectBankName;
        bankCard.add = mInitBank.getText().toString();
        
        commit(bankCard, code);
    }
    
    @Background
    void commit(BankCard bankCard, String code) {
        try {
            Api.createBackCard(bankCard, code);
            onCommitSuccess();
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onCommitSuccess() {
        ToastUtil.getDefault().show("添加成功");
        finish();
    }
    
}
