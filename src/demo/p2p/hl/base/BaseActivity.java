package demo.p2p.hl.base;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

@EActivity
public class BaseActivity extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }
    
    
    private void initActionBar() {
        getActionBar().setIcon(R.drawable.icon_actionbar_logo);
//        getActionBar().setIcon(R.drawable.icon_actionbar_logo_alert);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
    	case android.R.id.home:
		    finish();
		    break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @UiThread
    public void onApiException(ApiException ex) {
        
        if (ex instanceof ApiAuthorizedException) {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        } else {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        }
    }
    
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }
    
}
