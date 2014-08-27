package demo.p2p.hl.util;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    
    private static ToastUtil mInstance;
    private Context mContext;
    
    public synchronized static ToastUtil getDefault() {
        if (mInstance == null) {
            mInstance = new ToastUtil();
        }
        return mInstance;
    }
    
    private ToastUtil() {}
    
    public void init(Application context) {
        mContext = context;
    }
    
    public void show(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
    
}
