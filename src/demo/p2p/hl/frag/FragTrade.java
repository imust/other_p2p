package demo.p2p.hl.frag;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.view.View;
import android.widget.ListView;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.base.EsayAdapter;
import demo.p2p.hl.data.Loan;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.view.ItemViewLoan_;
import demo.p2p.hl.view.ViewLoanAction;

@EFragment(R.layout.frag_trade)
public class FragTrade extends BaseFragment implements BackPressed {

    @ViewById
    ListView mListView;
    
    @ViewById
    ViewLoanAction mLoanAction;

    EsayAdapter<Loan, ItemViewLoan_> mListAdapter;
    
    @AfterViews
    void init() {
        setTitle("交易中心");
        mListAdapter = new EsayAdapter<Loan, ItemViewLoan_>(getActivity()) {};
        mListView.setAdapter(mListAdapter);
        
        loadData();
        EventBus.getDefault().register(this);
        Lg.d("FragTrade", "event reg");
    }
    
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Lg.d("FragTrade", "event unreg");
    }
    
    public void onEventMainThread(Loan loan) {
        mLoanAction.show(loan);
    }
    
    
    @Background
    void loadData() {
        try {
            refresh(Api.getLoanList());
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void refresh(List<Loan> list) {
        mListAdapter.setList(list);
    }

    @Override
    public boolean onBackPressed() {
        if (mLoanAction.getVisibility() == View.VISIBLE) {
            mLoanAction.hide();
            return false;
        }
        return true;
    }
    
    
}
