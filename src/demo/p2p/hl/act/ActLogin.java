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
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.app.Application;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.view.CommonDialog;
import demo.p2p.hl.view.CommonDialogView.OnConfirmClickListener;

@EActivity(R.layout.act_login)
public class ActLogin extends BaseActivity {

    @ViewById
    EditText mUsername;
    
    @ViewById
    EditText mPassword;
    
    public static void start(Context context) {
        UserSession.get().clear();
        Intent intent = new Intent(context, ActLogin_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(false);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Application.REQ_REG && resultCode == RESULT_OK && data != null) {
            User user = (User) data.getSerializableExtra(ActReg.EXT_USER);
            if (user != null && user.url != null) {
                //把用户名密码填到输入框 备用
                mUsername.setText(user.phone);
                mPassword.setText(user.password);
                showBaofuDialog(user.url);
            }
            return;
        }
        
        if (requestCode == Application.REQ_REG_FROM_BAOFU) {
            onLogin();
            return;
        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    @AfterViews
    void init() {
        mUsername.setText("13880551453");
        mPassword.setText("1");
        
        mUsername.setText("17092848584");
        mPassword.setText("890218");
        
//      user = Api.login("livehl@126.com", "890218");
//      user = Api.login("13880803680", "1637496795a");
        checkLogin();
    }
    
    void checkLogin() {
        if (UserSession.get().isLogin()) {
            showLoginDialog();
            reLogin();
        }
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
            onLogin();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Background
    void reLogin() {
        try {
            User user = Api.getUser();
            onLoginSuccess(user);
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    void onLogin() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        showLoginDialog();
        login(username, password);
    }
    
    @Background
    void login(String username, String password) {
        try {
            User user = Api.login(username, password);
            onLoginSuccess(user);
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onLoginSuccess(User user) {
        cancelDialog();
        if (user != null) {
            // 有URL说明当前用户没有绑定到宝付
            if (user.url != null) {
                showBaofuDialog(user.url);
                return;
            }
            UserSession.get().setUser(user);
            ActMain.start(this);
        }
    }
    
    @Click
    void reg() {
        ActReg.start(this);
    }   
    
    void showLoginDialog() {
        cancelDialog();
        mCurrentDialog = new CommonDialog(this).setMessage("登录中...");
        mCurrentDialog.show();
    }
    
    void showBaofuDialog(final String url) {
        cancelDialog();
        mCurrentDialog = new CommonDialog(this).setMessage("您的帐户尚未绑定宝付, 即将前往宝付绑定.")
                .showConfirm()
                .showCancel()
                .setOnConfirmClickListener(new OnConfirmClickListener() {
                    public void OnConfirm() {
                        ActWeb.start(ActLogin.this, url, Application.REQ_REG_FROM_BAOFU);
                    }
                });
        mCurrentDialog.show();
    }
    
 
    
}
