package demo.p2p.hl.http;

import de.greenrobot.event.EventBus;
import demo.p2p.hl.event.EventNotLogin;
import demo.p2p.hl.event.EventToastError;

/**
 * http辅助类, 实现一个统一的认证及http基本配置
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class HttpHelper {

    /**
     * 各种授权认证
     * @param request
     * @return
     */
    public static String auth(HttpRequest request) {
        return result(request);
    }
    
    /**
     * 保存登录认证
     */
    public static void tryToSaveAuth(HttpRequest request) {
        String setCookie = request.header("Set-Cookie");
        if (setCookie != null) {
            
        }
    }
    
    /**
     * 发起调用处理结果
     * @param request
     * @return
     */
    public static String result(HttpRequest request) {
        
        try {
            if (request.ok()) {
                tryToSaveAuth(request);
                return request.body();
            }
            
            if (request.unAuthorized()) {
                EventBus.getDefault().post(new EventNotLogin());
                return null;
            }
            
            if (request.serverError()) {
                EventBus.getDefault().post(new EventToastError("服务端错误"));
                return null;
            }
            
            return null;
        } catch (Exception e) {
            EventBus.getDefault().post(new EventToastError(e.getMessage()));
            return null;
        }
    }
    
    public static String get(CharSequence url) {
        return auth(HttpRequest.get(url));
    }
    
    public static String get(CharSequence url, boolean isEncode, Object... params) {
        return auth(HttpRequest.get(url, isEncode, params));
    }
    
    
}
