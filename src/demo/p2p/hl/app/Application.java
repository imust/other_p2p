package demo.p2p.hl.app;

import demo.p2p.hl.http.HttpHelper;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.util.ToastUtil;

/**
 * beta.ddw0817.com/pwd.html
 * ryct
 * @date 2014-9-4
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class Application extends android.app.Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getDefault().init(this);
        ToastUtil.getDefault().init(this);
        UserSession.get().init(this);
    }
 
    
}
