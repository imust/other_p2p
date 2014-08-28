package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Fragment;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.event.EventMenuChange;
import demo.p2p.hl.frag.FragHome_;
import demo.p2p.hl.view.MenuView;
import demo.p2p.hl.view.MenuView_;

@EActivity(R.layout.act_main)
public class ActMain extends BaseActivity {
    
    private MenuView mMenuView;
    private SlidingMenu mSlidingMenu;
    private Fragment mCurrentFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    @AfterViews
    void init() {
        initSlidingMenu();
        initCurrentPage();
    }
    
    private void initCurrentPage() {
        replaceFragment(FragHome_.builder().build());
    }
    
    private void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mMenuView = MenuView_.build(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setBehindOffsetRes(R.dimen.s120);
        mSlidingMenu.setMenu(mMenuView);
    }
    
    public void onEventMainThread(EventMenuChange event) {
        replaceFragment(event.fragment);
    }
    
    @UiThread
    public void replaceFragment(Fragment fragment) {
        
        if (fragment != mCurrentFragment) {
            mCurrentFragment = fragment; 
            getFragmentManager().beginTransaction().replace(R.id.mContainer, fragment)
                .commitAllowingStateLoss();
        }
        mSlidingMenu.showContent();
    }
    

    
    
    
    
}
