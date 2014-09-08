package demo.p2p.hl.act;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.base.EsayAdapter;
import demo.p2p.hl.base.NavSpinnerAdapter;
import demo.p2p.hl.data.Bid;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.view.ItemViewBid_;

@EActivity(R.layout.act_bids)
public class ActBids extends BaseActivity implements OnNavigationListener {
    
    @ViewById
    ListView mListView;

    EsayAdapter<Bid, ItemViewBid_> mListAdapter;
	
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActBids_.class));
    }
    
    @AfterViews
    void init() {
        mListAdapter = new EsayAdapter<Bid, ItemViewBid_>(this) {};
        mListView.setAdapter(mListAdapter);
        loadData(false);
        
        SpinnerAdapter adapter = NavSpinnerAdapter.create(this, R.array.bid_list);
        
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(adapter, this);
        setTitle("");
    }
    
    @Override
    public boolean onNavigationItemSelected(int position, long arg1) {
        loadData(position == 1);
        return false;
    }

    @Background
    void loadData(boolean isNear) {
        try {
            refresh(Api.getBidList(isNear));
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void refresh(List<Bid> list) {
        mListAdapter.setList(list);
    }
    
    
}
