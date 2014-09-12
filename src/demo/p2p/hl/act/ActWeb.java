package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
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
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.util.Sp;

@EActivity(R.layout.act_web)
public class ActWeb extends BaseActivity {
    
    @ViewById
    WebView mWebView;
    
    @Extra("url")
    String url;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, ActWeb_.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
    
    
    @AfterViews
    void init() {
        setTitle("注册宝付账号");
        initWebView();
        if (url != null) {
            refresh(url);
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView.getSettings().setBuiltInZoomControls(true);  
        mWebView.getSettings().setJavaScriptEnabled(true);  
        mWebView.setWebViewClient(new WebViewClient(){
             @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 Lg.d(url);
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
    
    @UiThread
    void refresh(String url) {
        synCookies(url);
        mWebView.loadUrl(url);
    }
    
}
