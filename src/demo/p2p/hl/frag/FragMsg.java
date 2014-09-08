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
import demo.p2p.hl.data.Message;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.view.ItemViewMsg_;

@EFragment(R.layout.frag_msg)
public class FragMsg extends BaseFragment {

    @ViewById
    ListView mListView;

    EsayAdapter<Message, ItemViewMsg_> mListAdapter;
    
    @AfterViews
    void init() {
        
        setTitle("消息中心");
        
        mListAdapter = new EsayAdapter<Message, ItemViewMsg_>(getActivity()) {};
        mListView.setAdapter(mListAdapter);
        
        loadData();
        
    }
    
    @Background
    void loadData() {
        try {
            refresh(Api.getMessageList(false));
            updateMessageStatus();
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void refresh(List<Message> list) {
        mListAdapter.setList(list);
    }
    
    @Background
    void updateMessageStatus() {
        try {
            Api.updateMessageStatus();
        } catch (ApiException e) {
            Lg.e("updateMessageStatus", e.getErrorMessage());
        }
        
    }
}
