package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.act.ActAcc;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseFragment;

@EFragment(R.layout.frag_person)
public class FragPerson extends BaseFragment {

    @ViewById
    TextView mName;
    
    @ViewById
    TextView mPhone;
    
    @ViewById
    TextView mBalance;
    
    @AfterViews
    void init() {
        mName.setText(UserSession.get().getUser().realName);
        mPhone.setText("********" + UserSession.get().getUser().phone);
        mBalance.setText("账户余额: " + UserSession.get().getUser().balance);
    }
    
    @Click(R.id.mAcc)
    void onAccClick() {
        ActAcc.start(getActivity());
    }
    
    @Click(R.id.mBalance)
    void onBalanceClick() {
        
    }
    
    @Click(R.id.mLoadRecord)
    void onLoadRecordClick() {
        
    }
    
}
