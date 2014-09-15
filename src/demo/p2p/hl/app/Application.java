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
    
    public static final int REQ_REG = 0;
    public static final int REQ_REG_FROM_BAOFU = 1;
    
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getDefault().init(this);
        ToastUtil.getDefault().init(this);
        UserSession.get().init(this);
    }
 
    
}
