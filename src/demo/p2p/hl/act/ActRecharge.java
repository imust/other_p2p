package demo.p2p.hl.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import demo.p2p.hl.R;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;

@EActivity(R.layout.act_web)
public class ActRecharge extends ActWeb {

    @ViewById
    WebView mWebView;
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActRecharge_.class));
    }
    
    @AfterViews
    void init() {
        super.init();
        setTitle("充值");
        loadData();
    }
    
    @Background
    void loadData() {
        try {
            refresh(Api.recharge());
        } catch (ApiException e) {
            onApiException(e);
        }
    }
}
