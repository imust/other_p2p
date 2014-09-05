package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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
    
    @AfterViews
    void init() {
        getActionBar().setIcon(R.drawable.icon_actionbar_logo);
    }
    
    @Click
    @Background
    void login() {
        
        User user = null;
        try {
            user = Api.login("livehl@126.com", "890218");
        } catch (ApiException e) {
            onApiException(e);
        }
        
        if (user != null) {
            UserSession.open(user);
            ActMain.start(this);
            finish();
        }
    }
    
    
    
}
