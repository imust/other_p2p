package demo.p2p.hl.app;

import demo.p2p.hl.http.HttpHelper;
import demo.p2p.hl.util.ToastUtil;

public class Application extends android.app.Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getDefault().init(this);
        ToastUtil.getDefault().init(this);
    }
 
    
}
