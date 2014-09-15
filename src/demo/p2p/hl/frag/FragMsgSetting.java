package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;
import demo.p2p.hl.view.CommonDialog;

@EFragment(R.layout.frag_msg_setting)
public class FragMsgSetting extends BaseFragment {
    
    public static final String M_WEIXIN = "weixin";
    public static final String M_EMAIL = "email";
    public static final String M_SMS = "sms";

    @ViewById
	CheckBox mEmail;
    @ViewById
  	CheckBox mSms;
    @ViewById
	CheckBox mWeiXin;
    @ViewById
	CheckBox mHumanity;
	
    @AfterViews
    void init() {
        setTitle("系统设置");
    	setHasOptionsMenu(true);
    	parseMsgSetting();
    }
    

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
    	inflater.inflate(R.menu.msg_setting, menu);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.confirm:
		    showUpdateDialog();
			updateMsgSetting();
			return false;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    @Background
    public void updateMsgSetting() {
    	try {
    	    String msgSetting = createMsgSetting();
    	    boolean humanity = mHumanity.isChecked();
			Api.updateMsgSetting(humanity, msgSetting);
			onUpdateMsgSettingSuccess(humanity, msgSetting);
		} catch (ApiException e) {
			onApiException(e);
		}
    }
    
    @UiThread
    void onUpdateMsgSettingSuccess(Boolean humanity, String msgSetting) {
        cancelDialog();
        UserSession.get().getUser().ext.messageSetting = msgSetting;
        UserSession.get().getUser().ext.humanity = humanity;
        ToastUtil.getDefault().show("保存成功");
    }
    
    void showUpdateDialog() {
        cancelDialog();
        mCurrentDialog = new CommonDialog(getActivity()).setMessage("提交中...");
        mCurrentDialog.show();
    }
    
    public String createMsgSetting() {
    	StringBuffer s = new StringBuffer();
    	if (mWeiXin.isChecked()) {
    	    s.append(M_WEIXIN);
    		s.append("|");
    	}
    	if (mEmail.isChecked()) {
    	    s.append(M_EMAIL);
    		s.append("|");
    	}
    	if (mSms.isChecked()) {
    	    s.append(M_SMS);
    		s.append("|");
    	}
    	if (s.length() > 0) {
    		s.delete(s.length()-1, s.length());
    	}
    	
    	return s.toString();
    }
    
    public void parseMsgSetting() {
        User user = UserSession.get().getUser();
        mHumanity.setChecked(user.ext.humanity);
        
        String msgSetting = user.ext.messageSetting;
        if (msgSetting != null) {
            String[] msgSettings = msgSetting.split("\\|");
            for (String setting : msgSettings) {
                
                if (M_WEIXIN.equals(setting)) {
                    mWeiXin.setChecked(true);
                } else if (M_SMS.equals(setting)) {
                    mSms.setChecked(true);
                } else if (M_EMAIL.equals(setting)) {
                    mEmail.setChecked(true);
                }
            }
        }
    }
    
}
