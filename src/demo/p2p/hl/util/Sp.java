package demo.p2p.hl.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 简单的{@link SharedPreferences}封装工具类
 * 
 * @date 2014-8-13
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public class Sp {

    private Context mContext;

    public static final String PHONE_SP_NAME = "p2p_sp";

    public static final String SP_USER_SESSION = "user_session";

    public Sp(Context context) {
        mContext = context;
    }

    public Sp putString(String key, String value) {
        mContext.getSharedPreferences(PHONE_SP_NAME, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
        return this;
    }

    public String getString(String key, String defValue) {
        return mContext.getSharedPreferences(PHONE_SP_NAME,
                Context.MODE_PRIVATE).getString(key, defValue);
    }

    public String getString(String key) {
        return getString(key, null);
    }

}
