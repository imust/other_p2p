package demo.p2p.hl.act;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.data.Message;
import demo.p2p.hl.event.EventDrawerChange;
import demo.p2p.hl.event.EventExit;
import demo.p2p.hl.event.EventMenuChange;
import demo.p2p.hl.frag.FragHome_;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.view.MenuView;

@EActivity(R.layout.act_main)
public class ActMain extends BaseActivity {
    
    private Fragment mCurrentFragment;
    
    @ViewById
    MenuView mMenuView;
    
    @ViewById
    DrawerLayout mDrawer;
    
    ActionBarDrawerToggle mDrawerToggle;
    
    public static void start(Context context) {
        Intent intent = new Intent(context, ActMain_.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    	context.startActivity(intent);
    }
    
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
    
    @Override
    protected void onResume() {
        super.onResume();
        checkMessage();
    }
    
    @AfterViews
    void init() {
        initCurrentPage();
        initDrawer();
    }
    
    private void initCurrentPage() {
        replaceFragment(FragHome_.builder().build());
    }

    private void initDrawer() {
        
        mDrawerToggle = new ActionBarDrawerToggle(this, 
                mDrawer, R.drawable.icon_nav_drawer, R.string.drawer_open, R.string.drawer_close);
        
        mDrawer.setDrawerListener(mDrawerToggle);
        
        getActionBar().setHomeButtonEnabled(true);  
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    
    public void onEventMainThread(EventMenuChange event) {
        replaceFragment(event.fragment);
    }
    
    public void onEventMainThread(EventDrawerChange event) {
        if (event.open) {
            mDrawer.openDrawer(mMenuView);
        } else {
            mDrawer.closeDrawer(mMenuView);
        }
    }
    
    public  void onEventMainThread(EventExit event) {
        finish();
    }
    
    @UiThread
    public void replaceFragment(Fragment fragment) {
        if (mCurrentFragment == null ||
                !fragment.getClass().getName().equals(mCurrentFragment.getClass().getName())) {
            mCurrentFragment = fragment; 
            getFragmentManager().beginTransaction().replace(R.id.mContainer, fragment)
                .commitAllowingStateLoss();
        }
        mDrawer.closeDrawer(mMenuView);
    }
    
    @Background
    public void checkMessage() {
        try {
            List<Message> list = Api.getMessageList(true);
            refreshMsgUIStatus(list.size());
        } catch (ApiException e) {
            Lg.e("checkMessage", e.getErrorMessage());
        }
    }

    @UiThread
    public void refreshMsgUIStatus(int newMsgCount) {
        getActionBar().setIcon(newMsgCount > 0 ? 
                R.drawable.icon_actionbar_logo_alert :
                    R.drawable.icon_actionbar_logo_common);
        
        
        mMenuView.setNewMessageCount(newMsgCount);
    }
    
    
    
    
}
