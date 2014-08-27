package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseAct;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;

@EActivity(R.layout.act_login)
public class ActLogin extends BaseAct {

    @ViewById
    EditText mUsername;
    
    @ViewById
    EditText mPassword;
    
    @AfterViews
    void init() {
        
    }
    
    @Click
    @Background
    void login() {
        
        User user = null;
        try {
            user = Api.login("17092848584", "890218");
        } catch (ApiException e) {
            onApiException(e);
        }
        
        if (user != null) {
            UserSession.open(user);
            startActivity(new Intent(this, ActMain_.class));
        }
    }
    
    
    
}
