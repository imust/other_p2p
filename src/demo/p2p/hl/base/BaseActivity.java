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
import demo.p2p.hl.view.CommonDialog;

@EActivity
public class BaseActivity extends Activity {
    
    protected CommonDialog mCurrentDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }
    
    
    private void initActionBar() {
        getActionBar().setIcon(R.drawable.icon_actionbar_logo_common);
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
        cancelDialog();
        if (ex instanceof ApiAuthorizedException) {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        } else {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        }
    }
    
    protected void cancelDialog() {
        if (mCurrentDialog != null) {
            mCurrentDialog.dismiss();
            mCurrentDialog = null;
        }
    }
    
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }
    
    public void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }
    
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }
    
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }
    
}
