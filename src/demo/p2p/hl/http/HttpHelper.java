package demo.p2p.hl.http;

import org.json.JSONObject;

import android.app.Application;
import android.content.Context;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.event.EventNotLogin;
import demo.p2p.hl.event.EventToastError;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.util.Sp;

/**
 * http辅助类, 实现一个统一的认证及http基本配置
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class HttpHelper {

    private static HttpHelper mInstance;
    private Context mContext;
    private Sp mSp;
    
    public synchronized static HttpHelper getDefault() {
        if (mInstance == null) {
            mInstance = new HttpHelper();
        }
        return mInstance;
    }
    
    private HttpHelper() {}
    
    public void init(Application context) {
        mContext = context;
        mSp = new Sp(context);
    }
    
    /**
     * 各种授权认证
     * @param request
     * @return
     */
    public String auth(HttpRequest request) {
        String cookie = mSp.getString(Sp.SP_USER_SESSION, null);
        if (cookie != null) {
            request.header("Cookie", cookie);
        }
        return result(request);
    }
    
    /**
     * 保存登录认证
     */
    public void tryToSaveAuth(HttpRequest request) {
        String setCookie = request.header("Set-Cookie");
        if (setCookie != null) {
            new Sp(mContext).putString(Sp.SP_USER_SESSION, setCookie);
        }
    }
    
    /**
     * 发起调用处理结果
     * @param request
     * @return
     */
    public String result(HttpRequest request) {
        
        try {
            if (request.ok()) {
                tryToSaveAuth(request);
                String result = request.body();
                Lg.d("http", result);
                return result;
            }
            
            if (request.unAuthorized()) {
                EventBus.getDefault().post(new EventNotLogin());
                return null;
            }
            
            if (request.serverError()) {
                // {code:500, msg:xxxxx}
                String body = request.body();
                JSONObject jsonObject = new JSONObject(body);
                String msg = jsonObject.getString("msg");
                EventBus.getDefault().post(new EventToastError(msg != null ? msg :  "未知的服务端错误"));
                return null;
            }
            
            return null;
        } catch (Exception e) {
            EventBus.getDefault().post(new EventToastError(e.getMessage()));
            return null;
        }
    }
    
    public String get(CharSequence url) {
        return auth(HttpRequest.get(url));
    }
    
    public String get(CharSequence url, boolean isEncode, Object... params) {
        return auth(HttpRequest.get(url, isEncode, params));
    }
    
    
}
