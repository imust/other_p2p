package demo.p2p.hl.base;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import android.app.Fragment;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

@EFragment
public class BaseFragment extends Fragment {
    
    @UiThread
    public void onApiException(ApiException ex) {
        
        if (ex instanceof ApiAuthorizedException) {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        } else {
            ToastUtil.getDefault().show(ex.getErrorMessage());
        }
                
    }
}
