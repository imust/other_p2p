package demo.p2p.hl.frag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.base.BaseFragment;

@EFragment(R.layout.frag_home)
public class FragHome extends BaseFragment {

    @ViewById
    TextView mBalance;
    
    @AfterViews
    void init() {
        mBalance.setText(String.valueOf(UserSession.get().getUser().balance));
    }
    
}
