package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseAct;
import demo.p2p.hl.view.MenuView;
import demo.p2p.hl.view.MenuView_;

@EActivity(R.layout.act_main)
public class ActMain extends BaseAct {
    
    private MenuView mMenuView;
    
    @AfterViews
    void init() {
        SlidingMenu sm = new SlidingMenu(this);
        mMenuView = MenuView_.build(this);
        
        sm.setMode(SlidingMenu.LEFT);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        sm.setBehindOffsetRes(R.dimen.width_120);
        sm.setMenu(mMenuView);
    }
}
