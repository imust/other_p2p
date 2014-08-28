package demo.p2p.hl.http;

import android.app.Application;
import android.content.Context;
import demo.p2p.hl.http.HttpRequest.HttpRequestException;
import demo.p2p.hl.http.api.ApiAuthorizedException;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.JsonUtil;
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
    public String auth(HttpRequest request) throws ApiException {
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
    public String result(HttpRequest request) throws ApiException {
        
        try {
            Lg.d("http", request.url().toString());
            if (request.ok()) {
                tryToSaveAuth(request);
                String result = request.body();
                Lg.d("http", "200:" + result);
                return result;
            }
            
            if (request.unAuthorized()) {
                Lg.d("http", "401");
                throw new ApiAuthorizedException();
            }
            
            if (request.serverError()) {
                // {code:500, msg:xxxxx}
                String body = request.body();
                String msg = JsonUtil.getString(body, "msg");
                Lg.d("http", "500:" + body);
                throw new ApiException(msg != null ? msg :  "未知的服务端错误");
            }
            
        } catch (HttpRequestException e) {
            throw new ApiException("网络连接异常");
        }
        
        throw new ApiException("未知的服务端错误");
    }
    
    public String get(CharSequence url) throws ApiException {
        return auth(HttpRequest.get(url));
    }
    
    public String get(CharSequence url, boolean isEncode, Object... params) throws ApiException {
        return auth(HttpRequest.get(url, isEncode, params));
    }
    
    
}
