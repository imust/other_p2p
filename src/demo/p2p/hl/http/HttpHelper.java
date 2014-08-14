package demo.p2p.hl.http;

/**
 * http辅助类, 实现一个统一的认证及http基本配置
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class HttpHelper {

    public static HttpRequest auth(HttpRequest request) {
        return request.acceptJson();
    }
    
    public static HttpRequest get(CharSequence url) {
        return auth(HttpRequest.get(url));
    }
    
    public static HttpRequest get(CharSequence url, boolean isEncode, Object... params) {
        return auth(HttpRequest.get(url, isEncode, params));
    }
    
    
}
