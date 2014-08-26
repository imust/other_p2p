package demo.p2p.hl.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.event.EventNotLogin;
import demo.p2p.hl.event.EventToastError;

public class BaseAct extends Activity {
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
//    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    public void onEventMainThread(EventToastError event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }
    
    public void onEventMainThread(EventNotLogin event) {
        onEventMainThread(new EventToastError("未登录"));
    }
    
    
}
