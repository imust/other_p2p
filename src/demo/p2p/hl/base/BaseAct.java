package demo.p2p.hl.base;

import android.app.Activity;
import android.os.Bundle;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

public class BaseAct extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
    
    public void onApiException(ApiException ex) {
        
        if (ex instanceof ApiAuthorizedException) {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        }
                
    }
    
}
