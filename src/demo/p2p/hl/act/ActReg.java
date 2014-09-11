package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

@EActivity(R.layout.act_reg)
public class ActReg extends BaseActivity {
    
    @ViewById
    EditText mRealName;
    @ViewById
    EditText mID;
    @ViewById
    EditText mCode;
    @ViewById
    EditText mPhone;
    @ViewById
    EditText mPassword;
    @ViewById
    Button mSendCodeButton;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActReg_.class));
    }
    
    @AfterViews
    void init() {
        setTitle("用户注册");
    }
    
    @Click(R.id.mSendCodeButton)
    void onSendCodeClick() {
        sendCode();
    }
    
    @Background
    void sendCode() {
        try {
            String phone = mPhone.getText().toString();
            Api.sendPhoneCode(phone, false);
            onSendCodeSuccess();
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onSendCodeSuccess() {
        ToastUtil.getDefault().show("验证码已发送到手机");
        mSendCodeButton.setEnabled(false);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reg, menu);
        return super.onCreateOptionsMenu(menu);
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.confirm:
            onCommit();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    
    void onCommit() {
        String code = mCode.getText().toString();
        String phone = mPhone.getText().toString();
        String realNmae = mRealName.getText().toString();
        String idCard = mID.getText().toString();
        String password = mPassword.getText().toString();
        commit(phone, realNmae, password, idCard, code);
    }
    
    @Background
    void commit(String phone, String realName, String password, String idCard, String code) {
        try {
            Api.reg(realName, password, idCard, phone, code);
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
