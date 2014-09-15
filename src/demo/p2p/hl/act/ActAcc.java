package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.User;

@EActivity(R.layout.act_acc)
public class ActAcc extends BaseActivity {
    
	
	@ViewById
	TextView mName;
	@ViewById
	TextView mId;
	@ViewById
	TextView mPhone;
	@ViewById
	TextView mEmail;
	
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActAcc_.class));
    }
    
    @AfterViews
    void init() {
        User user = UserSession.get().getUser();
    	mName.setText(user.realName);
    	mEmail.setText(user.email + "********");
    	mPhone.setText("********" + user.phone);
    	mId.setText("**************" + user.idCard);
    }
    
    
    
}
