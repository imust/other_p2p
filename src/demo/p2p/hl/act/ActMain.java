package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
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
import demo.p2p.hl.event.EventMenuChange;
import demo.p2p.hl.frag.FragHome_;
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
        context.startActivity(new Intent(context, ActMain_.class));
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
        
        getActionBar().setDisplayHomeAsUpEnabled(true);  
        getActionBar().setHomeButtonEnabled(true);  
        getActionBar().setIcon(R.drawable.icon_actionbar_logo_alert);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
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
    

    
    
    
    
}
