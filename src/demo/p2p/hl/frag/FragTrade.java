package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;

import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseFragment;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;

@EFragment(R.layout.frag_trade)
public class FragTrade extends BaseFragment {

    
    @AfterViews
    void init() {
        loadData();
    }
    
    @Background
    void loadData() {
        try {
            Api.getLoanList();
        } catch (ApiException e) {
            onApiException(e);
        }
    }
}
