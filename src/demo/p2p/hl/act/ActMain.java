package demo.p2p.hl.act;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseAct;

@EActivity(R.layout.act_main)
public class ActMain extends BaseAct {
    
    
    
    @Click(R.id.login)
    void click() {
        SlidingMenu sm = new SlidingMenu(this);
        sm.setMode(SlidingMenu.LEFT);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        sm.setMenu(R.layout.menu);
        sm.setBehindOffsetRes(R.dimen.width_120);
    }
}
