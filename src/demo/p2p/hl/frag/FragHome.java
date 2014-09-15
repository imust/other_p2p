package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.data.User;

@EFragment(R.layout.frag_home)
public class FragHome extends BaseFragment {

    @ViewById
    TextView mYesterdayE;
    @ViewById
    TextView mLock;
    @ViewById
    TextView mBalance;
    @ViewById
    TextView mBorrowOut;
    @ViewById
    TextView mTotalE;
    
    
    @AfterViews
    void init() {
        setTitle("首页");
        User user = UserSession.get().getUser();
        mBalance.setText(String.valueOf(user.balance + user.borrowOut));
        mYesterdayE.setText(user.ue.getYesterdayString());
        mLock.setText(String.valueOf(user.lockMoney));
        mBorrowOut.setText(String.valueOf(user.borrowOut));
        mTotalE.setText(user.ue.getTotalString());
    }
    
}
