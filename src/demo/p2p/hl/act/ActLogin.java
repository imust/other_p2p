package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;

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
    
    @Background
    @AfterViews
    void init() {
        if (UserSession.get().hasSession()) {
            reLogin();
        }
    }
    
    @Background
    void reLogin() {
        User user = null;
        try {
            user = Api.getUser();
        } catch (ApiException e) {
            onApiException(e);
        }
        
        if (user != null) {
            UserSession.get().setUser(user);
            ActMain.start(this);
        }
    }
    
    @Click
    @Background
    void login() {
        
        User user = null;
        try {
            user = Api.login("17092848584", "890218");
//            user = Api.login("livehl@126.com", "890218");
//            user = Api.login("13880803680", "1637496795a");
//            user = Api.login("13880551453", "1");
        } catch (ApiException e) {
            onApiException(e);
        }
        
        if (user != null) {
            
            if (user.url != null) {
                ActWeb.start(this, user.url);
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
    
    
}
