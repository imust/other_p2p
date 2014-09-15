package demo.p2p.hl.base;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import android.app.Fragment;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;
import demo.p2p.hl.view.CommonDialog;

@EFragment
public class BaseFragment extends Fragment {
    
    protected CommonDialog mCurrentDialog;
    
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
    
    public void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }
}
