package demo.p2p.hl.frag;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.widget.ListView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.base.EsayAdapter;
import demo.p2p.hl.data.Loan;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.view.ItemViewLoan_;

@EFragment(R.layout.frag_trade)
public class FragTrade extends BaseFragment {

    @ViewById
    ListView mListView;

    EsayAdapter<Loan, ItemViewLoan_> mListAdapter;
    
    @AfterViews
    void init() {
        
        mListAdapter = new EsayAdapter<Loan, ItemViewLoan_>(getActivity()) {};
        mListView.setAdapter(mListAdapter);
        
        loadData();
        
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
}
