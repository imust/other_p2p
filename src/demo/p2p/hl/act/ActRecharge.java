package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.Lg;
import demo.p2p.hl.util.Sp;
import demo.p2p.hl.util.ToastUtil;
import demo.p2p.hl.view.CommonDialog;

@EActivity(R.layout.act_recharge)
public class ActRecharge extends BaseActivity {

    @ViewById
    WebView mWebView;
    @ViewById
    EditText mBalance;
    
    @ViewById
    View mInputContainer;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActRecharge_.class));
    }
    
    @AfterViews
    void init() {
        setTitle("充值");
        initWebView();
    }
    
    @Click(R.id.mCommit)
    void onCommitClick() {
        String str = mBalance.getText().toString();
        if (TextUtils.isEmpty(str)) {
            ToastUtil.getDefault().show("请输入充值金额");
            return;
        }
        int amount = Integer.parseInt(str);
        loadData(amount);
        showCommitDialog();
    }
    
    void showCommitDialog() {
        mCurrentDialog = new CommonDialog(this).setMessage("请求中...");
        mCurrentDialog.show();
    }
    
    @Background
    void loadData(int amount) {
        try {
            String url = Api.recharge(amount);
            onLoadSuccess(url);
        } catch (ApiException e) {
            onApiException(e);
        }
    }
    
    @UiThread
    void onLoadSuccess(String url) {
        refresh(url);
        cancelDialog();
        mWebView.setVisibility(View.VISIBLE);
        mInputContainer.setVisibility(View.GONE);
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
    
    void refresh(String url) {
        synCookies(url);
        mWebView.loadUrl(url);
    }
}
