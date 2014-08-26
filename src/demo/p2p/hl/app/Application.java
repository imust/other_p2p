package demo.p2p.hl.app;

import demo.p2p.hl.http.HttpHelper;

public class Application extends android.app.Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getDefault().init(this);
    }
 
    
}
