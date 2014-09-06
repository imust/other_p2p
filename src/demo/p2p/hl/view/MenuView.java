package demo.p2p.hl.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.act.ActLogin_;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.event.EventExit;
import demo.p2p.hl.event.EventMenuChange;
import demo.p2p.hl.frag.FragHome_;
import demo.p2p.hl.frag.FragMsgSetting_;
import demo.p2p.hl.frag.FragPerson_;
import demo.p2p.hl.frag.FragTrade_;

@EViewGroup(R.layout.menu)
public class MenuView extends LinearLayout {

    @ViewById
    TextView mName;
    
    public MenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context) {
        super(context);
    }

    @AfterViews
    void init() {
        this.setOrientation(VERTICAL);
        this.setBackgroundResource(R.drawable.bg_menu);
        mName.setText(UserSession.get().getUser().realName);
    }
    
    void post(Fragment fragment) {
        EventBus.getDefault().post(new EventMenuChange(fragment));
    }

    @Click
    void home() {
        post(FragHome_.builder().build());
    }
    
    @Click
    void trade() {
        post(FragTrade_.builder().build());
    }
    
    @Click
    void person() {
        post(FragPerson_.builder().build());
    }
    
    @Click
    void setting() {
        post(FragMsgSetting_.builder().build());
    }
    
    @Click
    void change() {
        ActLogin_.start(getContext());
    }
    
    @Click
    void exit() {
        EventBus.getDefault().post(new EventExit());
    }
    
}
