package demo.p2p.hl.app;

import android.content.Context;
import demo.p2p.hl.data.User;
import demo.p2p.hl.util.Sp;

public class UserSession {
    
    public static UserSession mInstance;
    
    private User mUser;
    
    private Context mContext;
    
    private Sp mSp;
    
    private UserSession() { 
    }
    
    public synchronized static UserSession get() {
        if (mInstance == null) {
            mInstance = new UserSession();
        }
        return mInstance;
    }
    
    public void init(Application application) {
        mContext = application;
    	mSp = new Sp(mContext);
    }
    
    public String getSession() {
        return mSp.getString(Sp.SP_USER_SESSION, null);
    }
    
    public void setSession (String session) {
        mSp.putString(Sp.SP_USER_SESSION, session);
    }
    
    public void clearSession() {
        setSession(null);
    }
    
    public boolean hasSession() {
        return getSession() != null;
    }
    
    public User getUser() {
        return mUser;
    }
    
    // 保存user登录状态
    public void setUser(User user) {
        mSp.putBoolean(Sp.SP_USER_LOGIN, true);
        mUser = user;
    }
    
    public boolean isLogin() {
        return mSp.getBoolean(Sp.SP_USER_LOGIN, false);
    }

    public void clear() {
        mSp.putBoolean(Sp.SP_USER_LOGIN, false);
        mUser = null;
    	clearSession();
    }
    
}
