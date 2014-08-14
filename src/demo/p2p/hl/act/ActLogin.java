package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.EditText;
import demo.p2p.hl.config.Config;
import demo.p2p.hl.http.HttpHelper;

@EActivity
public class ActLogin extends Activity {
 
    @ViewById
    EditText mUsername;
    
    @ViewById
    EditText mPwd;
    
    @AfterViews
    void afterView() {
        
    }
    
    @Click
    void onLogin() {
        String result = 
        HttpHelper.get(Config.createUri("user", "login"), true, 
                "username", "17092848584" , "pwd", "890218").body();
        
    }
    
    
}
