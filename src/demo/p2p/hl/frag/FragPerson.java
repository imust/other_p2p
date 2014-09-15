package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.act.ActAcc;
import demo.p2p.hl.act.ActBankCardList;
import demo.p2p.hl.act.ActBids;
import demo.p2p.hl.act.ActRecharge;
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
        setTitle("个人中心");
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
    
    @Click(R.id.mBankCard)
    void onBankCardClick() {
        ActBankCardList.start(getActivity());
    }
    
    @Click(R.id.mRecharge)
    void onRechargeClick() {
        ActRecharge.start(getActivity());
    }
    
    @Click(R.id.mBid)
    void onBidClick() {
        ActBids.start(getActivity());
    }
    
}
