package demo.p2p.hl.base;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

@EActivity
public class BaseActivity extends Activity {
    
    @UiThread
    public void onApiException(ApiException ex) {
        
        if (ex instanceof ApiAuthorizedException) {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        } else {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        }
                
    }
    
}
