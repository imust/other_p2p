package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.util.Sp;

@EActivity(R.layout.act_web)
public class ActRecharge extends BaseActivity {

    @ViewById
    WebView mWebView;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActRecharge_.class));
    }
    
    @AfterViews
    void init() {
        setTitle("充值");
        initWebView();
        loadData();
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView.getSettings().setBuiltInZoomControls(true);  
        mWebView.getSettings().setJavaScriptEnabled(true);  
        mWebView.setWebViewClient(new WebViewClient(){
             @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 Lg.e(url);
                 synCookies(url);
                 view.loadUrl(url);
                 return true;
            }
        });
    }
    
    /** 
     * 同步一下cookie 
     */  
    public void synCookies(String url) {  
        CookieSyncManager.createInstance(this);  
        CookieManager cookieManager = CookieManager.getInstance();  
        cookieManager.setAcceptCookie(true);  
        cookieManager.removeSessionCookie();//移除  
        cookieManager.setCookie(url, new Sp(this).getString(Sp.SP_USER_SESSION));  
        CookieSyncManager.getInstance().sync();  
    }  
    
    @Background
    void loadData() {
        try {
            refresh(Api.recharge());
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void refresh(String url) {
        synCookies(url);
        mWebView.loadUrl(url);
    }
}
