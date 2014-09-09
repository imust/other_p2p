package demo.p2p.hl.http;

import android.app.Application;
import android.content.Context;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.http.HttpRequest.HttpRequestException;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.JsonUtil;
import demo.p2p.hl.util.Lg;

/**
 * http辅助类, 实现一个统一的认证及http基本配置
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class HttpHelper {

    public static final int TIMEOUT_READ = 15000;
    public static final int TIMEOUT_CONNECT = 5000;
    
    private static HttpHelper mInstance;
    private Context mContext;
    
    public synchronized static HttpHelper getDefault() {
        if (mInstance == null) {
            mInstance = new HttpHelper();
        }
        return mInstance;
    }
    
    private HttpHelper() {}
    
    public void init(Application context) {
        mContext = context;
    }
    
    /**
     * 各种授权认证
     * @param request
     * @return
     */
    public HttpRequest auth(HttpRequest request) throws ApiException {
        String cookie = UserSession.get().getSession();
        if (cookie != null) {
            request.header("Cookie", cookie);
        }
        request.connectTimeout(TIMEOUT_CONNECT);
        request.readTimeout(TIMEOUT_READ);
        return request;
    }
    
    /**
     * 保存登录认证
     */
    public void saveAuth(HttpRequest request) {
        String setCookie = request.header("Set-Cookie");
        if (setCookie != null) {
            UserSession.get().setSession(setCookie);
        }
    }
    
    /**
     * 发起调用处理结果
     * @param request
     * @return
     */
    public String result(HttpRequest request) throws ApiException {
        
        try {
            
            Lg.d("http", request.url().toString());
            
            String body = request.body();
            int code = request.code();
            
            if (request.ok()) {
                saveAuth(request);
                Lg.d("http", "200:" + body);
                return body;
            }
            
            if (request.unAuthorized()) {
                Lg.e("http", "401");
                throw new ApiAuthorizedException();
            }
            
            if (request.serverError()) {
                // {code:500, msg:xxxxx}
                String msg = JsonUtil.getString(body, "msg");
                Lg.e("http", "500:" + body);
                throw new ApiException(msg != null ? msg :  "未知的服务端错误");
            }
            
            Lg.e("http", code + ":" + request.url().toString() + "\nbody:" + body);
            
        } catch (HttpRequestException e) {
            throw new ApiException("网络连接异常");
        }
        
        throw new ApiException("未知的服务端错误");
    }
    
    public String get(CharSequence url) throws ApiException {
        return result(auth(HttpRequest.get(url)));
    }
    
    public String get(CharSequence url, boolean isEncode, Object... params) throws ApiException {
        return result(auth(HttpRequest.get(url, isEncode, params)));
    }
    
    public String post(CharSequence url, Object... params) throws ApiException {
        try {
            HttpRequest request = HttpRequest.post(url);
            request = auth(request);
            if (params != null) {
                for (int i=0 ; i<params.length / 2;i++) {
                    request.form(params[i*2], params[i*2+1]);
                }
            }
            return result(request);
        } catch (Exception e) {
            throw new ApiException("网络连接异常");
        }
    }
    
    
}
