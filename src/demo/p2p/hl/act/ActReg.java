package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.app.Application;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;
import demo.p2p.hl.view.CommonDialog;

@EActivity(R.layout.act_reg)
public class ActReg extends BaseActivity {
    
    public static final String EXT_USER = "user";
    
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
    
    public static void start(Activity context) {
        context.startActivityForResult(new Intent(context, ActReg_.class), Application.REQ_REG);
    }
    
    @AfterViews
    void init() {
        setTitle("用户注册");
    }
    
    @Click(R.id.mSendCodeButton)
    void onSendCodeClick() {
        sendCode();
        mSendCodeButton.setEnabled(false);
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
        mSendCodeButton.setText("已发送");
        ToastUtil.getDefault().show("验证码已发送到手机");
    }
    
    @UiThread
    void onSendCodeFail() {
        mSendCodeButton.setEnabled(true);
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
        showRegDialog();
    }
    
    @Background
    void commit(String phone, String realName, String password, String idCard, String code) {
        try {
            User user = Api.reg(realName, password, idCard, phone, code);
            user.phone = phone;
            user.password = password;
            onCommitSuccess(user);
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onCommitSuccess(User user) {
        cancelDialog();
        ToastUtil.getDefault().show("注册成功, 即将跳转到宝付");
        Intent intent = new Intent();
        intent.putExtra(EXT_USER, user);
        setResult(RESULT_OK, intent);
        finish();
    }
    
    void showRegDialog() {
        cancelDialog();
        mCurrentDialog = new CommonDialog(this).setMessage("注册中...");
        mCurrentDialog.show();
    }
}
