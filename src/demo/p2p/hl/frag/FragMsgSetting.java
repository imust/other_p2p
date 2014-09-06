package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.event.EventDrawerChange;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;

@EFragment(R.layout.frag_msg_setting)
public class FragMsgSetting extends BaseFragment {

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
    	setHasOptionsMenu(true);
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
			updateMsgSetting();
			break;
		}
    	return true;
    }
    
    @Background
    public void updateMsgSetting() {
    	try {
			Api.updateMsgSetting(mHumanity.isChecked(), createMsgSetting());
			post(new EventDrawerChange(true));
		} catch (ApiException e) {
			onApiException(e);
		}
    }
    
    public String createMsgSetting() {
    	StringBuffer s = new StringBuffer();
    	if (mWeiXin.isChecked()) {
    		s.append("weixin|");
    	}
    	if (mEmail.isChecked()) {
    		s.append("email|");
    	}
    	if (mSms.isChecked()) {
    		s.append("sms|");
    	}
    	if (s.length() > 0) {
    		s.delete(s.length()-1, s.length());
    	}
    	
    	return s.toString();
    }
    
    
}
